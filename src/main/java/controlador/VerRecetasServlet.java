/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelo.Comentario;
import modelo.Receta;

@WebServlet(name = "VerRecetasServlet", urlPatterns = {"/VerRecetasServlet"})
public class VerRecetasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Receta> lista = new ArrayList<>();
        Map<Integer, Integer> likeMap = new HashMap<>();
        Map<Integer, List<Comentario>> comentarioMap = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apprecetas", "root", "");

            // 1. Obtener recetas
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM recetas");

            while (rs.next()) {
                Receta receta = new Receta(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("ingredientes"),
                    rs.getString("preparacion"),
                    rs.getString("autor")
                );
                lista.add(receta);
            }
            rs.close();
            st.close();

            // 2. Obtener likes por receta
            String sqlLikes = "SELECT receta_id, COUNT(*) AS total FROM likes GROUP BY receta_id";
            PreparedStatement psLikes = con.prepareStatement(sqlLikes);
            rs = psLikes.executeQuery();
            while (rs.next()) {
                likeMap.put(rs.getInt("receta_id"), rs.getInt("total"));
            }
            rs.close();
            psLikes.close();

            // 3. Obtener comentarios con nombre del autor
          String sqlComentarios = """
            SELECT c.receta_id, c.comentario, c.fecha, u.nombre 
            FROM comentarios c
            JOIN usuarios u ON u.id = c.usuario_id
            ORDER BY c.fecha ASC
            """;

            PreparedStatement psCom = con.prepareStatement(sqlComentarios);
            rs = psCom.executeQuery();

                while (rs.next()) {
                int recetaId = rs.getInt("receta_id");
                String autor = rs.getString("nombre");
                String texto = rs.getString("comentario");
                Timestamp fecha = rs.getTimestamp("fecha");

                comentarioMap
                    .computeIfAbsent(recetaId, k -> new ArrayList<>())
                    .add(new Comentario(autor, texto, fecha));  
            }


            rs.close();
            psCom.close();
            con.close();

            // Enviar datos a la JSP
            request.setAttribute("listaRecetas", lista);
            request.setAttribute("likeMap", likeMap);
            request.setAttribute("comentarioMap", comentarioMap);

            request.getRequestDispatcher("recetas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("perfil.jsp");
        }
    }
}

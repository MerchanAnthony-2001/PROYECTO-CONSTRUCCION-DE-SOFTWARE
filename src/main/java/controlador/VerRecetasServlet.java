/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Receta;

/**
 *
 * @author CodeAngel369
 */
@WebServlet(name = "VerRecetasServlet", urlPatterns = {"/VerRecetasServlet"})
public class VerRecetasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Receta> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apprecetas", "root", "");

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
            con.close();

            request.setAttribute("listaRecetas", lista);
            request.getRequestDispatcher("recetas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("perfil.jsp");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        

        // Validar si hay usuario en sesión
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp?error=debes_iniciar_sesion");
            return;
        }
        

        int usuarioId = (Integer) session.getAttribute("usuarioId");
        int recetaId = Integer.parseInt(request.getParameter("receta_id"));


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/apprecetas", "root", "");

            // Evitar duplicados (solo un me encanta por usuario por receta)
            PreparedStatement check = con.prepareStatement(
                "SELECT * FROM likes WHERE usuario_id = ? AND receta_id = ?");
            check.setInt(1, usuarioId);
            check.setInt(2, recetaId);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO likes (usuario_id, receta_id) VALUES (?, ?)");
                ps.setInt(1, usuarioId);
                ps.setInt(2, recetaId);
                ps.executeUpdate();
                ps.close();
            }

            rs.close();
            check.close();
            con.close();

            response.sendRedirect("VerRecetasServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("recetas.jsp?error=like");
        }
    }
}

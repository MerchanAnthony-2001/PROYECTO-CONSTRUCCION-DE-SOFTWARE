/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import config.ConexionBD;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ComentarioServlet")
public class ComentarioServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ComentarioServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // Validar sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("login.jsp?error=debes_iniciar_sesion");
                return;
            }

            int recetaId = Integer.parseInt(request.getParameter("receta_id"));
            int usuarioId = (int) session.getAttribute("usuarioId");
            String comentario = request.getParameter("comentario");

            // Insertar comentario en la BD
            String sql = "INSERT INTO comentarios (receta_id, usuario_id, comentario, fecha) VALUES (?, ?, ?, NOW())";

            try (
                Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)
            ) {
                ps.setInt(1, recetaId);
                ps.setInt(2, usuarioId);
                ps.setString(3, comentario);
                ps.executeUpdate();
            }

        } catch (SQLException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error al insertar comentario", e);
        }

        response.sendRedirect("VerRecetasServlet");
    }
}

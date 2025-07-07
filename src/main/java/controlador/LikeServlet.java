/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import config.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LikeServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp?error=debes_iniciar_sesion");
            return;
        }

        try {
            int usuarioId = (int) session.getAttribute("usuarioId");
            int recetaId = Integer.parseInt(request.getParameter("receta_id"));

            String checkSQL = "SELECT 1 FROM likes WHERE usuario_id = ? AND receta_id = ?";
            String insertSQL = "INSERT INTO likes (usuario_id, receta_id) VALUES (?, ?)";

            try (
                Connection con = ConexionBD.obtenerConexion();
                PreparedStatement checkStmt = con.prepareStatement(checkSQL)
            ) {
                checkStmt.setInt(1, usuarioId);
                checkStmt.setInt(2, recetaId);

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        try (PreparedStatement insertStmt = con.prepareStatement(insertSQL)) {
                            insertStmt.setInt(1, usuarioId);
                            insertStmt.setInt(2, recetaId);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }

            response.sendRedirect("VerRecetasServlet");

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "ID de receta inválido: " + request.getParameter("receta_id"), e);
            response.sendRedirect("recetas.jsp?error=parametros_invalidos");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en la base de datos al registrar like", e);
            response.sendRedirect("recetas.jsp?error=db_like");
        }
    }
}

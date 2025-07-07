/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelo.Usuario;
import config.ConexionBD;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        if (correo == null || contrasena == null ||
            correo.trim().isEmpty() || contrasena.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=campos_vacios");
            return;
        }

        //  Sanitización simple 
        correo = correo.trim().replaceAll("[<>\"']", "");
        contrasena = contrasena.trim();

        try (Connection con = ConexionBD.obtenerConexion()) {

            if (con == null) {
                response.sendRedirect("login.jsp?error=conexion");
                return;
            }

            String sql = "SELECT * FROM usuarios WHERE correo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, correo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("contrasena");

                        if (BCrypt.checkpw(contrasena, storedHash)) {
                            Usuario usuario = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                correo,
                                null
                            );
                            HttpSession session = request.getSession(true);
                            session.setAttribute("usuario", usuario);
                            session.setAttribute("usuarioId", usuario.getId());

                            response.sendRedirect("perfil.jsp");
                        } else {
                            response.sendRedirect("login.jsp?error=credenciales");
                        }
                    } else {
                        response.sendRedirect("login.jsp?error=usuario_no_encontrado");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=sql");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=general");
        }
    }
}

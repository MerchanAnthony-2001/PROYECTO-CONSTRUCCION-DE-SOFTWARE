package controlador;

import org.mindrot.jbcrypt.BCrypt;
import org.apache.commons.text.StringEscapeUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Sanitización
        String nombre = StringEscapeUtils.escapeHtml4(request.getParameter("nombre"));
        String correo = StringEscapeUtils.escapeHtml4(request.getParameter("correo"));
        String contrasena = request.getParameter("contrasena");

        // Validación
        if (nombre == null || nombre.isBlank() ||
            correo == null || correo.isBlank() ||
            contrasena == null || contrasena.isBlank()) {
            response.sendRedirect("register.jsp?error=campos_vacios");
            return;
        }

        // Hash de la contraseña
        String contrasenaHash = BCrypt.hashpw(contrasena, BCrypt.gensalt(12));

        // try-with-resources
        try (
            Connection con = config.ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)")
        ) {
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, contrasenaHash); 

            int filas = ps.executeUpdate();

            if (filas > 0) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=insert_fallido");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            response.sendRedirect("register.jsp?error=correo_duplicado");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=servidor");
        }
    }
}

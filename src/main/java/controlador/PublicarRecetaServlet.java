package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
import modelo.Usuario;

@WebServlet(name = "PublicarRecetaServlet", urlPatterns = {"/PublicarRecetaServlet"})
public class PublicarRecetaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp?error=no_sesion");
            return;
        }

        String titulo = request.getParameter("titulo");
        String ingredientes = request.getParameter("ingredientes");
        String preparacion = request.getParameter("preparacion");

        //  Validación básica de campos
        if (titulo == null || titulo.trim().isEmpty() ||
            ingredientes == null || ingredientes.trim().isEmpty() ||
            preparacion == null || preparacion.trim().isEmpty()) {

            response.sendRedirect("publicar.jsp?error=campos_vacios");
            return;
        }

        //  Sanitización básica
        titulo = titulo.trim().replaceAll("[<>\"']", "");
        ingredientes = ingredientes.trim().replaceAll("[<>\"']", "");
        preparacion = preparacion.trim().replaceAll("[<>\"']", "");

        //  Uso de try-with-resources
        try (
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apprecetas", "root", "");
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO recetas (titulo, ingredientes, preparacion, autor) VALUES (?, ?, ?, ?)")
        ) {
            ps.setString(1, titulo);
            ps.setString(2, ingredientes);
            ps.setString(3, preparacion);
            ps.setString(4, usuario.getNombre());

            ps.executeUpdate();

            response.sendRedirect("VerRecetasServlet");

        } catch (SQLException e) {
            e.printStackTrace(); // En producción deberías usar un logger
            response.sendRedirect("publicar.jsp?error=bd");
        }
    }
}

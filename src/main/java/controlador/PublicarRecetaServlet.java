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
import java.sql.PreparedStatement;
import modelo.Usuario;

/**
 *
 * @author CodeAngel369
 */
@WebServlet(name = "PublicarRecetaServlet", urlPatterns = {"/PublicarRecetaServlet"})
public class PublicarRecetaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String titulo = request.getParameter("titulo");
        String ingredientes = request.getParameter("ingredientes");
        String preparacion = request.getParameter("preparacion");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apprecetas", "root", "");

            String sql = "INSERT INTO recetas (titulo, ingredientes, preparacion, autor) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, ingredientes);
            ps.setString(3, preparacion);
            ps.setString(4, usuario.getNombre());

            ps.executeUpdate();
            ps.close();
            con.close();

            response.sendRedirect("recetas.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("publicar.jsp?error=1");
        }
    }
}
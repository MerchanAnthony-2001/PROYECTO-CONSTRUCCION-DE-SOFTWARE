/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import config.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author CodeAngel369
 */
@WebServlet("/ComentarioServlet")
public class ComentarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recetaId = Integer.parseInt(request.getParameter("receta_id"));
        int usuarioId = (int) request.getSession().getAttribute("usuarioId");
        String comentario = request.getParameter("comentario");

        try (Connection con = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO comentarios (receta_id, usuario_id, comentario) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, recetaId);
            ps.setInt(2, usuarioId);
            ps.setString(3, comentario);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("VerRecetasServlet");
    }
}

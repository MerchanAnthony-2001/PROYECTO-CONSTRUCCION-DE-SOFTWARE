<%-- 
    Document   : publicar
    Created on : 23 jun 2025, 6:36:34 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<%@ page session="true" %>
<%
    // Verificamos si el usuario ha iniciado sesión
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Publicar Receta - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css"> 
</head>
<body>
    <header>
        <h1> Publicar nueva receta</h1>
    </header>

    <main>
        <form action="PublicarRecetaServlet" method="post">
            <label for="titulo">Título de la receta:</label><br>
            <input type="text" id="titulo" name="titulo" required><br><br>

            <label for="ingredientes">Ingredientes:</label><br>
            <textarea id="ingredientes" name="ingredientes" rows="5" cols="50" required></textarea><br><br>

            <label for="preparacion">Preparación:</label><br>
            <textarea id="preparacion" name="preparacion" rows="7" cols="50" required></textarea><br><br>

            <input type="submit" value="Publicar receta">
        </form>

        <p><a href="perfil.jsp">← Volver a mi perfil</a></p>
    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>

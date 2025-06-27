<%-- 
    Document   : perfil
    Created on : 23 jun 2025, 6:36:00 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<%@ page session="true" %>
<%
    // Verificamos si el usuario ha iniciado sesión
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        // Si no hay usuario en sesión, redirigimos al login
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Perfil - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <header>
        <h1>Bienvenido, <%= usuario.getNombre() %> 👋</h1>
    </header>

    <main>
        <h2>Tu Información</h2>
        <ul>
            <li><strong>Nombre:</strong> <%= usuario.getNombre() %></li>
            <li><strong>Correo:</strong> <%= usuario.getCorreo() %></li>
        </ul>

        <div class="perfil-acciones">
    <a href="publicar.jsp" class="btn-accion">➕ Publicar nueva receta</a>
    <a href="VerRecetasServlet" class="btn-accion">📖 Ver recetas de otros usuarios</a>
    <a href="LogoutServlet" class="btn-accion logout">🚪 Cerrar sesión</a>
        </div>

    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>

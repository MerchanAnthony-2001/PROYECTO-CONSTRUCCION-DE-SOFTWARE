<%-- 
    Document   : register
    Created on : 19 jun 2025, 7:39:53 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrarse - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <header>
        <h1>Registro de Usuario</h1>
    </header>

    <main>
        <form action="RegistroServlet" method="post">
            <label for="nombre">Nombre:</label><br>
            <input type="text" id="nombre" name="nombre" required><br><br>

            <label for="correo">Correo electrónico:</label><br>
            <input type="email" id="correo" name="correo" required><br><br>

            <label for="contrasena">Contraseña:</label><br>
            <input type="password" id="contrasena" name="contrasena" required><br><br>

            <input type="submit" value="Registrarse">
        </form>

        <p>¿Ya tienes una cuenta? <a href="login.jsp">Inicia sesión aquí</a>.</p>
        
        <%
    String error = request.getParameter("error");
    if ("campos_vacios".equals(error)) {
%>
    <p style="color:red;">️ Completa todos los campos.</p>
<%
    } else if ("correo_duplicado".equals(error)) {
%>
    <p style="color:red;"> Este correo ya está registrado.</p>
<%
    } else if ("conexion_bd".equals(error)) {
%>
    <p style="color:red;"> Error de conexión a la base de datos.</p>
<%
    } else if ("insert_fallido".equals(error)) {
%>
    <p style="color:red;">️ No se pudo registrar el usuario.</p>
<%
    } else if ("servidor".equals(error)) {
%>
    <p style="color:red;"> Error interno del servidor.</p>
<%
    }
%>

    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>

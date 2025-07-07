<%-- 
    Document   : login
    Created on : 19 jun 2025, 7:45:49 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - AppRecetas</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <!-- Encabezado con la imagen (opcional) -->
    <header class="encabezado">
        <h1>Iniciar Sesión</h1>
    </header>

    <main>
        <!-- 1️⃣  autocomplete="on" permite al navegador recordar correos/usuarios -->
        <form action="LoginServlet" method="post" autocomplete="on">
            <label for="correo">Correo electrónico:</label>
            <input type="email"
                   id="correo"
                   name="correo"
                   required>

            <label for="contrasena">Contraseña:</label>
            <input type="password"
                   id="contrasena"
                   name="contrasena"
                   required>

            <input type="submit" value="Iniciar sesión" class="boton">
        </form>

        <p>¿No tienes cuenta? <a href="register.jsp">Regístrate aquí</a>.</p>

       <%
    String error = request.getParameter("error");
    if ("campos_vacios".equals(error)) {
%>
    <p style="color:red;">⚠️ Completa todos los campos.</p>
<%
    } else if ("credenciales".equals(error)) {
%>
    <p style="color:red;">❌ Contraseña incorrecta.</p>
<%
    } else if ("usuario_no_encontrado".equals(error)) {
%>
    <p style="color:red;">⚠️ Usuario no registrado.</p>
<%
    } else if ("conexion".equals(error)) {
%>
    <p style="color:red;">🔌 Error de conexión con la base de datos.</p>
<%
    } else if ("sql".equals(error)) {
%>
    <p style="color:red;">❌ Error interno en la base de datos.</p>
<%
    } else if ("general".equals(error)) {
%>
    <p style="color:red;">⚠️ Error inesperado. Intenta más tarde.</p>
<%
    }
%>

    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>


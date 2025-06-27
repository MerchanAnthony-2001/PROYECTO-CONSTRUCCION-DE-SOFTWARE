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

        <%-- Mensaje de error si login falla --%>
        <%
            String error = request.getParameter("error");
            if ("1".equals(error)) {
        %>
            <p style="color:red;">Correo o contraseña incorrectos.</p>
        <%
            }
        %>
    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>


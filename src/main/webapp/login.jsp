<%-- 
    Document   : login
    Created on : 19 jun 2025, 7:45:49 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css"> <!-- Opcional -->
</head>
<body>
    <header>
        <h1>Iniciar Sesión</h1>
    </header>

    <main>
        <form action="LoginServlet" method="post">
            <label for="correo">Correo electrónico:</label><br>
            <input type="email" id="correo" name="correo" required><br><br>

            <label for="contrasena">Contraseña:</label><br>
            <input type="password" id="contrasena" name="contrasena" required><br><br>

            <input type="submit" value="Iniciar sesión">
        </form>

        <p>¿No tienes cuenta? <a href="register.jsp">Regístrate aquí</a>.</p>

        <% 
            String error = request.getParameter("error");
            if (error != null && error.equals("1")) {
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

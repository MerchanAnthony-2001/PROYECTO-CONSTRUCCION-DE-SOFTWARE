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
    <link rel="stylesheet" href="estilos.css"> <!-- Opcional, si tienes CSS -->
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
    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>

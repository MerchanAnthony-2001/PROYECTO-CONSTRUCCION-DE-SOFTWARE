<%-- 
    Document   : recetas
    Created on : 23 jun 2025, 7:10:21 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Receta" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recetas Compartidas - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css"> <!-- Opcional -->
</head>
<body>
    <header>
        <h1>📖 Recetas Compartidas</h1>
    </header>

    <main>
        <c:if test="${empty listaRecetas}">
            <p>No hay recetas publicadas aún.</p>
        </c:if>

        <c:forEach var="receta" items="${listaRecetas}">
            <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 15px;">
                <h2>${receta.titulo}</h2>
                <p><strong>Ingredientes:</strong><br>${receta.ingredientes}</p>
                <p><strong>Preparación:</strong><br>${receta.preparacion}</p>
                <p><em>Publicado por: ${receta.autor}</em></p>
            </div>
        </c:forEach>

        <p><a href="perfil.jsp">← Volver a mi perfil</a></p>
    </main>

    <footer>
        <p>&copy; 2025 AppRecetas</p>
    </footer>
</body>
</html>

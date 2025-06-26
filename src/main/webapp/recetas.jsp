<%-- 
    Document   : recetas
    Created on : 23 jun 2025, 7:10:21 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Receta" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recetas Compartidas - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <header>
        <h1>📖 Recetas Compartidas</h1>
    </header>

    <main>
        <!-- Mostrar cuántas recetas se encontraron -->
        <c:if test="${not empty listaRecetas}">
            <p><strong>Total de recetas encontradas:</strong> ${fn:length(listaRecetas)}</p>
        </c:if>

        <!-- Mostrar mensaje si no hay recetas -->
        <c:if test="${empty listaRecetas}">
            <p style="color:gray;">No hay recetas publicadas aún.</p>
        </c:if>

        <!-- Listado de recetas -->
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

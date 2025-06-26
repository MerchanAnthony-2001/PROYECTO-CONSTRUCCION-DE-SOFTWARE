<%-- 
    Document   : recetas
    Created on : 23 jun 2025, 7:10:21 p. m.
    Author     : CodeAngel369
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ page import="modelo.Receta" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>Recetas Compartidas - AppRecetas</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css">
</head>

<script>
function darMeEncanta(recetaId) {
    fetch('LikeServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'receta_id=' + recetaId
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('No autorizado o error en el servidor');
        }
        // Si quieres evitar que el mismo usuario dé más de un like, verifica eso en el servidor
        const contador = document.getElementById("like-count-" + recetaId);
        let actual = parseInt(contador.textContent);
        contador.textContent = actual + 1;
    })
    .catch(error => {
        alert("Debes iniciar sesión para dar 'Me encanta'");
        console.error(error);
    });
}
</script>

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
        <div class="tarjeta-receta">
            <h2>${receta.titulo}</h2>
            <p><strong>Ingredientes:</strong><br>${receta.ingredientes}</p>
            <p><strong>Preparación:</strong><br>${receta.preparacion}</p>
            <p><em>Publicado por: ${receta.autor}</em></p>

           
            <!-- Mostrar botón "Me encanta" solo si el usuario ha iniciado sesión -->
            <c:if test="${not empty sessionScope.usuarioId}">
                <button onclick="darMeEncanta(${receta.id})">❤️ Me encanta</button>
                <span id="like-count-${receta.id}">
                <c:out value="${likeMap[receta.id]}" default="0"/>
                </span>
               

                <!-- Formulario de Comentario -->
                <form action="ComentarioServlet" method="post">
                    <input type="hidden" name="receta_id" value="${receta.id}">
                    <textarea name="comentario" placeholder="Escribe tu comentario..." required></textarea>
                    <input type="submit" value="Comentar">
                </form>
            </c:if>

            <!-- Mostrar comentarios si existen -->
            <c:if test="${not empty comentarioMap[receta.id]}">
                <div class="comentarios">
                    <h4>💬 Comentarios:</h4>
                    <ul>
                        <c:forEach var="coment" items="${comentarioMap[receta.id]}">
                            <li><strong>${coment.autor}:</strong> ${coment.texto}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <hr>
        </div>
    </c:forEach>

    <p><a href="perfil.jsp">← Volver a mi perfil</a></p>
</main>

<footer>
    <p>&copy; 2025 AppRecetas</p>
</footer>
</body>
</html>

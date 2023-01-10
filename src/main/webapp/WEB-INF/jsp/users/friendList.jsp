<%@ page import="org.springframework.samples.dobble.user.User" %>
<%@ page import="org.springframework.samples.dobble.game.Game" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="nt4h" tagdir="/WEB-INF/tags" %>

<nt4h:layout pageName="friends">
    <h2>Friends</h2>

    <button style="font-size: 140%;" type="button" data-toggle="modal" data-target="#modalWindow">
        Chat &nbsp &nbsp<span
        class="glyphicon glyphicon-comment"></span>
    </button >
    

    

    <h1>Search for friends</h1>
    <input type="text" id="search-input">
    <ul id="suggestion-list"></ul>
    <%@ include file="/WEB-INF/jsp/comments/CommentsListing.jsp" %>
    
  <script>
    class Search {
        constructor() {
            this.searchInput = document.getElementById("search-input");
            this.suggestionList = document.getElementById("suggestion-list");
            this.searchInput = document.getElementById("search-input");
            if (this.searchInput) {
                this.suggestionList = document.getElementById("suggestion-list");
                this.searchInput.addEventListener("input", this.search.bind(this));
            }
            }

        async search() {
            const searchTerm = this.searchInput.value;
            if (searchTerm.length < 1) {
            this.suggestionList.innerHTML = "";
            history.pushState({}, "", "/friends");
            return;
            }
            const response = await fetch('/friends/search?term='+searchTerm);
            const suggestions = await response.json();
            this.updateSuggestionList(suggestions);
            history.pushState({}, "", `/friends`);
        }

        updateSuggestionList(suggestions) {
            this.suggestionList.innerHTML = "";
            for (const suggestion of suggestions) {
            const li = document.createElement("li");
            const button = document.createElement("button");
            button.style.cssText = "background-color: green; color: white; border: none; width: 10%;";
            button.innerText = "Add Friend";
            button.addEventListener("click", function() {
                window.location.href = '/friends/add/'+suggestion;
            });
            li.innerText = suggestion;
            li.appendChild(button);
            this.suggestionList.appendChild(li);
            }
        }
        }

        new Search();
  </script>
    <style>
        .modal {
            left: 81%;
            top: 30%;
            width: 20%; /* Ocupar todo el ancho de la pantalla */
            height: 60%; /* Ocupar toda la altura de la pantalla */
            overflow: auto; /* Añadir barras de desplazamiento si el contenido es muy largo */
            background-color: rgba(0, 0, 0, 0); /* Añadir transparencia al color de fondo */
          }
  
        /* Estilo del contenido de la ventana modal */
        .modal-content {
          background-color: rgba(0,0,0,0.4);  /* Color de fondo claro */
          margin: 15% auto; /* Margen alrededor del contenido */
          padding: 20px; /* Espacio alrededor del contenido */
          border: 1px solid #888; /* Añadir un borde alrededor del contenido */
        }
  
        /* Estilo del botón para cerrar la ventana modal */
        .close {
          color: #aaa; /* Color del texto del botón */
          float: right; /* Alinear el botón a la derecha */
          font-size: 28px; /* Tamaño de la fuente del texto */
          font-weight: bold; /* Añadir negrita al texto */
        }
  
        /* Estilo del botón para cerrar la ventana modal cuando el usuario pasa el ratón por encima */
        .close:hover,
        .close:focus {
          color: white; /* Cambiar el color del texto al pasar el ratón por encima */
          text-decoration: none; /* Quitar el subrayado del enlace */
          cursor: pointer; /* Cambiar el cursor al*/
        }
    </style>
    <table id="friends" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px">Actions</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friendsList}" var="localUser">
            <tr>
                <td><c:out value="${localUser.username}"/></td>
                <td>
					<a href="/friends/remove/${localUser.username}"><span class="glyphicon glyphicon-minus-sign" aria-hden="true"></span></a> 
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${page > 0}">
            <spring:url value="/friends?page={previous}" var="previous">
                <spring:param name="previous" value="${page-1}"/>
            </spring:url>
            <a href="${fn:escapeXml(previous)}" class="btn">Previous</a>
        </c:if>
        <c:if test="${isNext}">
            <spring:url value="/friends?page={next}" var="next">
                <spring:param name="next" value="${page+1}"/>
            </spring:url>
            <a href="${fn:escapeXml(next)}" class="btn">Next</a>
        </c:if>
</nt4h:layout>

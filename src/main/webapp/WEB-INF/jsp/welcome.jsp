<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<dobble:layout pageName="home">
  <%@ include file="/WEB-INF/jsp/comments/CommentsListing.jsp" %>
    
    <div class="row">
    <h2 class="title">&nbspProject ${title}</h2>
    <h2>&nbsp</h2>
    <p><h2 class="subtitle">&nbsp Group ${group}</h2></p>
    <p><ul>
        <c:forEach items="${persons}" var="person">
            <h4 class="persons-list">${person.firstName}&nbsp${person.lastName}</h4>
        </c:forEach>
     
    </ul></p>
</div>
<div class="row">
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
        body {
          background-image: url("/resources/images/background.png");
        }
    
        .title {
          font-family: 'Press Start 2P', cursive;
          color: #080808;
          text-align: center;
          font-size: 3em;
          margin: 20px;
        }
    
        .subtitle {
          font-family: 'Press Start 2P', cursive;
          color: #030303;
          text-align: center;
          font-size: 2em;
          margin: 20px;
        }
    
        .persons-list {
          position: relative;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          font-family: 'Press Start 2P', cursive;
          color: #850808;
          text-align: center;
          font-size: 1.5em;
          list-style: none;
        }
    
    </style>
    <script>
        // Obtén el elemento del body
var body = document.getElementsByTagName("body")[0];

// Establece la posición inicial de la imagen de fondo
body.style.backgroundPosition = "0px 0px";

// Crea una función que se ejecutará cada 10 milisegundos
setInterval(function() {
  // Obtén la posición actual de la imagen de fondo
  var currentPos = body.style.backgroundPosition;
  // Divide la posición en dos variables para poder modificarlas individualmente
  var xPos = currentPos.split(" ")[0];
  var yPos = currentPos.split(" ")[1];
  // Incrementa la posición en 10 píxeles
  xPos = parseInt(xPos) + 1 + "px";
  yPos = parseInt(yPos) + 1 + "px";
  // Establece la nueva posición de la imagen de fondo
  body.style.backgroundPosition = xPos + " " + yPos;
    }, 100);
</script>
    
    
    
    
</div>
</dobble:layout>

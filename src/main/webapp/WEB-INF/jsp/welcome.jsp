<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<dobble:layout pageName="home">
    <div class="row">
    <h2 class="title">Project ${title}</h2>
    <h2>&nbsp</h2>
    <p><h2 class="subtitle">Group ${group}</h2></p>
    <p><ul>
        <c:forEach items="${persons}" var="person">
            <li class="persons-list">${person.firstName}&nbsp${person.lastName}</li>
        </c:forEach>
        
    </ul></p>
</div>
<div class="row">
    <style>
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

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="tournaments">
   
    <c:if test="${param.error!=null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-alert"></span>
            <label>
                ERROR:  
            </label>
            <c:out value="${param.error}"></c:out>
        </div>
    </c:if>
    
    <div class="tournament-list-header">
        <h2>Tournaments</h2>
        <a href="/tournaments/new">
            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                </span>
            New Tournament
        </a>
    </div>
    <table  id="tournaments-table">
        <tr>
            <td>
                <table id="tournaments-table-head" class="table">
                    <thead>
                      <tr>
                        <th>ID<span onclick="sortTable(0)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -5%;">Num. Games<span onclick="sortTable(1)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -20%;">Owner</th>
                        <th style="position: relative; left: -9%;"><span class="glyphicon glyphicon-lock"></span>
                          <span onclick="sortTable(3)" class="glyphicon glyphicon-sort"></span></th>
                        <th></th>
                        <th style="position: relative; left: 12%;">Num. Players<span onclick="sortTable(5)" class="glyphicon glyphicon-sort"></span></th>
                      </tr>
                    </thead>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div id="tournaments-table-body-div">
                <table id="tournaments-table-body" class="table">
                <tbody>
                    <c:forEach items="${tournaments}" var="tournament" varStatus="status">
                        <spring:url value="/tournaments/{tournamentId}" var="tournamentUrl">
                            <spring:param name="tournamentId" value="${tournament.id}"/>
                        </spring:url>
                            <tr onclick="joinTournament('${tournament.id}','${tournament.isPrivate()}')">
                                <td>
                                    <c:out value="${tournament.id}"/> 
                                </td>
                                <td>
                                    <c:out value="${numpartidas[status.index]}"/> 
                                </td>
                                <td>
                                    <c:out value="${tournament.owner}"></c:out>
                                </td>
                                <td>
                                    <c:if test="${tournament.isPrivate()}">  
                                        <span class="glyphicon glyphicon-lock private-tournament-lock"></span>
                                    </c:if>
                                </td>
                                <td>
                                    <a onmouseenter="enableJoinTournament=false" onmouseleave="enableJoinTournament=true" href="${tournamentUrl}">Details</a>
                                </td>
                                
                                <td class="num-players" style="text-align:center;">
                                    <c:out value="${tournament.numUsers}/${tournament.maxPlayers}"/>
                                </td>
                            </tr>
                           <dobble:modal id="${tournament.id}-access-modal" className="access-modal">
                               <h2 style="text-align: center;">Access Code</h2>
                               <form:form  id="${tournament.id}-form" action="${tournamentUrl}/join" method="POST" modelAttribute="accessCode">
                                   <div class="form">
                                       <input id="${tournament.id}-access-modal-input" name="accessCode"  class="access-modal-input" placeholder="Enter access code...">
                                       <input id="${tournament.id}-access-modal-submit" class="access-modal-submit" type="submit" value="Join">
                                    </div>
                                </form:form>
                            </dobble:modal> 
                            
                            
                        </c:forEach>
                        
                    </tbody>
                </table>    
            </div>
            </td>
        </tr>
        <style>
            body {
                background-image: url("/resources/images/background.png");
            }
        </style>
    </table>

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

    function goto(url){
            window.location=url
    }
    
    function showAccessModal(bool){
            document.getElementById("access-modal").style.visibility=bool
    }

    let enableJoinTournament = true
    function joinTournament(tournamentId, isPrivate) {
        
        if (!enableJoinTournament) return
        if(eval(isPrivate)) {
            const modal = document.getElementById(tournamentId+"-access-modal")
            modal.style.display="block"
        } else {
            const form = document.getElementById(tournamentId+"-form").submit()
        }      
       
    }
    document.addEventListener('keydown',function(e) { 
    if (e.keyCode === 27) { 
    const modals = document.getElementsByClassName("modal")
    for (let modal of modals){
    modal.style.display = "none"

    }
    } 
})

    const numPlayersIter = document.getElementsByClassName("num-players")
    for (numPlayers of numPlayersIter){
        const playersText = numPlayers.innerText.split("/")
        const currentPlayers = playersText[0], maxPlayers = playersText[1]
        if (currentPlayers >= maxPlayers) numPlayers.style.color="#ff3c38"
        else if (currentPlayers == maxPlayers-1) numPlayers.style.color="#ffc921"
        else numPlayers.style.color="#29cb3d"
        numPlayers.style.fontWeight="bold"
    }

    function sortTable(n) {
  var table, rows, switching, i, x,x0,x1, y,y0,y1, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("tournaments-table-body");
  switching = true;
  //Set the sorting direction to ascending:
  dir = "asc"; 
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 0; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /*check if the two rows should switch place,
      based on the direction, asc or desc:*/
      if (dir == "asc") {
        if ((n==1 || n==3) && x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }

       
        if ((n==0) && parseInt(x.innerHTML) > parseInt(y.innerHTML)){
            shouldSwitch= true;
            break;
        }

        x0=parseInt(x.innerHTML.split("/")[0])
        x1=parseInt(x.innerHTML.split("/")[1])
        y0=parseInt(y.innerHTML.split("/")[0])
        y1=parseInt(y.innerHTML.split("/")[1])
        if (n==5 && (x1-x0 > y1-y0)){
            shouldSwitch= true;
            break;
        }
      } else if (dir == "desc") {
        if ((n==1 || n==3) && x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }

       
        if ((n==0) && parseInt(x.innerHTML) < parseInt(y.innerHTML)){
            shouldSwitch= true;
            break;
        }
        x0=parseInt(x.innerHTML.split("/")[0])
        x1=parseInt(x.innerHTML.split("/")[1])
        y0=parseInt(y.innerHTML.split("/")[0])
        y1=parseInt(y.innerHTML.split("/")[1])
        if (n==5 && (x1-x0 < y1-y0)){
            shouldSwitch= true;
            break;
        }
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      /*If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again.*/
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
    
    </script>
</dobble:layout>

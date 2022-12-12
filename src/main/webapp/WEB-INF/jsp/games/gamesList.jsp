<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
   
    <c:if test="${param.error!=null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-alert"></span>
            <label>
                ERROR:  
            </label>
            <c:out value="${param.error}"></c:out>
        </div>
    </c:if>
    
    <div class="game-list-header">
        <h2>Games</h2>
        <a href="/games/new">
            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                </span>
            New Game
        </a>
    </div>
    <table id="games-list"  >
        <tr>
            <td>
                
                <table id="games-list-head" class="table">
                <thead>
                    <tr>
                        <th>ID<span onclick="sortTable(0)" class="glyphicon glyphicon-sort"></span></th>
                        <th>Gamemode<span onclick="sortTable(1)" class="glyphicon glyphicon-sort"></span></th>
                        <th>Owner</th>
                        <th><span class="glyphicon glyphicon-lock"></span>
                            <span onclick="sortTable(3)" class="glyphicon glyphicon-sort"></span></th>
                        <th></th>
                        <th style="width:150px">Num. Players<span onclick="sortTable(5)" class="glyphicon glyphicon-sort"></span></th>
                    </tr>
                </thead>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div id="games-list-body-div">
                <table id="games-list-body" class="table">
                <tbody>
            
                    <c:forEach items="${games}" var="game">
                        <spring:url value="/games/{gameId}" var="gameUrl">
                            <spring:param name="gameId" value="${game.id}"/>
                        </spring:url>
                        
                           
                            <tr onclick="joinGame('${game.id}','${game.isPrivate()}')">
                                
                                <td>
                                     <c:out value="${game.id}"/>
                                    
                                </td>
                                <td>
                                    <c:out value="${game.gamemode}"/>
                                </td>
                                <td>
                                    <c:out value="${game.owner}"/>
                                </td>
                                <td>
                                    <c:if test="${game.isPrivate()}">  
                                        <span class="glyphicon glyphicon-lock private-game-lock"></span>
                                    </c:if>
                                </td>
                                
                                <td>
                                    <a onmouseenter="enableJoinGame=false" onmouseleave="enableJoinGame=true" href="${gameUrl}">Details</a>
                                </td>
                                
                                <td class="num-players" style="text-align:center;">
                                    <c:out value="${game.numUsers}/${game.maxPlayers}"/>
                                </td>
                            </tr>
                           <dobble:modal id="${game.id}-access-modal" className="access-modal">
                               <h2 style="text-align: center;">Access Code</h2>
                               <form:form  id="${game.id}-form" action="${gameUrl}/join" method="POST" modelAttribute="accessCode">
                                   <div class="form">
                                       <input id="${game.id}-access-modal-input" name="accessCode"  class="access-modal-input" placeholder="Enter access code...">
                                       <input id="${game.id}-access-modal-submit" class="access-modal-submit" type="submit" value="Join">
                                    </div>
                                </form:form>
                            </dobble:modal> 
                            
                            
                        </c:forEach>
                        
                    </tbody>
                </table>    
            </div>
            </td>
        </tr>
        
    </table>

    <script>
        function goto(url){
            window.location=url
    }
    
    function showAccessModal(bool){
            document.getElementById("access-modal").style.visibility=bool
    }

    let enableJoinGame = true
    function joinGame(gameId, isPrivate) {
        
        if (!enableJoinGame) return
        if(eval(isPrivate)) {
            const modal = document.getElementById(gameId+"-access-modal")
            modal.style.display="block"
        } else {
            const form = document.getElementById(gameId+"-form").submit()
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
  table = document.getElementById("games-list-body");
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

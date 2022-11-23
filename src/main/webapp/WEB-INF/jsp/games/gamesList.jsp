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
    <table id="games-table" class=" table" >
        <thead>
        <tr>
            <th>ID<span class="glyphicon glyphicon-sort"></span></th>
            <th>Gamemode<span class="glyphicon glyphicon-sort"></span></th>
            <th>Owner</th>
            <th><span class="glyphicon glyphicon-lock"></span>
                <span class="glyphicon glyphicon-sort"></span></th>
            <th></th>
            <th style="width:150px">Num. Players<span class="glyphicon glyphicon-sort"></span></th>
        </tr>

        </thead>
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
                        <a href="${gameUrl}">Details</a>
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
    <script>
        function goto(url){
            window.location=url
    }
    
    function showAccessModal(bool){
            document.getElementById("access-modal").style.visibility=bool
    }

    function joinGame(gameId, isPrivate) {
        if(eval(isPrivate)) {
            const modal = document.getElementById(gameId+"-access-modal")
            modal.style.display="block"
        } else {
            const form = document.getElementById(gameId+"-form")
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
    console.log(numPlayersIter)
    for (numPlayers of numPlayersIter){
        const playersText = numPlayers.innerText.split("/")
        const currentPlayers = playersText[0], maxPlayers = playersText[1]
        if (currentPlayers >= maxPlayers) numPlayers.style.color="#ff3c38"
        else if (currentPlayers == maxPlayers-1) numPlayers.style.color="#ffc921"
        else numPlayers.style.color="#29cb3d"
        numPlayers.style.fontWeight="bold"
    }

    
    </script>
</dobble:layout>

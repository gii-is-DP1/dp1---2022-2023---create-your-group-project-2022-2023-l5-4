<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <style>
       
        .access-modal > .modal-content {
            border-radius: 25px;
            width: 25%;
            aspect-ratio: 1.5;            
            margin: 10% auto;
           
        }
        
        .access-modal-submit{
            bottom:25px;
            height:50px;
            width: 100px;
            font-size: large;
            font-weight: bold;
            border-radius: 10px;
            border-width: 0;
          
        }
       

        .access-modal-input{
            bottom:50%;
            border-radius: 5px;
            font-family: cartoon-toy;
            font-size: 18px;
        }
        
        .access-modal input {
            transform: translate(-50%, 0);
            left:50%;
            position: absolute;
            height: 50px;
            width: 150px;
        }
        .game-list-header {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }

        .alert > * {
            margin: 5px;
        }
    </style>
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
    <table id="games-table" class=" table hoverable" >
        <thead>
        <tr>
            <th>ID</th>
            <th>Gamemode</th>
            <th>Owner</th>
            <th>Num. Players</th>
            <th></th>
            <th></th>
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
                        <c:out value="${game.numUsers}/${game.maxPlayers}"/>
                    </td>
                    <td>
                        <c:if test="${game.isPrivate()}">  
                            <span class="glyphicon glyphicon-lock private-game-lock"></span>
                        </c:if>
                    </td>

                        <td>
                            <a href="${gameUrl}">Details</a>
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

    
    </script>
</dobble:layout>

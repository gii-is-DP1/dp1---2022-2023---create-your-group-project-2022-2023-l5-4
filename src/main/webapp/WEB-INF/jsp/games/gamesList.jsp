<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <dobble:errorMessage/>
    
    <div class="game-list-header">
        <h2>Games</h2>
        <a href="/games/new">
            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                </span>
            New Game
        </a>
    </div>
        <div class="game-list">
            <div class="game-list-head">
              <div style="width:10%; text-indent: 15px" class="header-field">ID<span onclick="sortTable(0)" class="glyphicon glyphicon-sort"></span></div>
              <div style="width:20%" class="header-field">Gamemode<span onclick="sortTable(1)" class="glyphicon glyphicon-sort"></span></div>
              <div style="width:15%; text-align:center" class="header-field">Owner</div>
              <div style="width:15%; text-align:center" class="header-field"><span class="glyphicon glyphicon-lock"></span><span onclick="sortTable(3)" class="glyphicon glyphicon-sort"></span></div>
              <div style="width:20%; text-align:center" class="header-field"></div>
              <div style="width:20%; text-align: right; transform: translateX(-15px);" class="header-field">Num. Players<span onclick="sortTable(5)" class="glyphicon glyphicon-sort"></span></div>
            </div>
            <div class="game-list-body">
                <c:forEach items="${games}" var="game">
                    <spring:url value="/games/{gameId}" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    
                       
                <div class="body-line" onclick="join('${game.id}','${game.isPrivate() && !user.getCurrentGame().equals(game)}','${user.getCurrentGame()!=null}')">
                    <div style="width:10%; text-indent: 15px" class="body-field">
                        <c:out value="${game.id}"/>
                    </div>
                    <div style="width:20%" class="body-field">
                        <c:out value="${game.gamemode}"/>
                    </div>
                    <div style="width:20%; text-align:center" class="body-field">
                        <c:out value="${game.owner}"/>
                    </div>
                    <div style="width:15%; text-align:center" class="body-field">
                        <c:if test="${game.isPrivate()}">  
                                            <span class="glyphicon glyphicon-lock private-game-lock"></span>
                        </c:if>
                    </div>
                    <div style="width:15%; text-align:center" class="body-field">
                        <a onmouseenter="enableJoin=false" onmouseleave="enableJoin=true" href="${gameUrl}">Details</a>
                    </div>
                    <div style="width:20%; text-align: right; transform: translateX(-15px);" class="body-field num-players">
                        <c:out value="${game.numUsers}/${game.maxPlayers}"/>
                    </div>
                </div>
                <form:form  id="${game.id}-form" action="${gameUrl}/join" method="POST" modelAttribute="accessCode">
                    <c:if test="${game.isPrivate() && !user.getCurrentGame().equals(game)}">  
                        <dobble:modal id="${game.id}-access-modal" className="access-modal">
                            <h1 class="modal-title">Access Code</h1>
                            <div class="form">
                                <input id="${game.id}-access-modal-input" name="accessCode" class="modal-input access-modal-input" placeholder="Enter access code...">
                                <div id="${game.id}-access-modal-submit" class="modal-submit access-modal-submit" onclick="checkIsOnAnotherGame('${game.id}','${user.getCurrentGame()!=null}')">Join</div>
                            </div>
                        </dobble:modal> 
                        </c:if>
                    </form:form>
                    <dobble:modal id="${game.id}-on-another-game-modal" className="on-another-game-modal">
                        <h1 style="text-align: center;" class="modal-title">You are already in another game</h1>
                        <div class="form">
                            <div id="${game.id}-on-another-game-modal-submit-current" class="modal-submit on-another-game-modal-submit-current" onclick="joinSubmit('${user.getCurrentGame().getId()}','${user.getCurrentGame().isOnPlay()}')">current</div>
                            <div id="${game.id}-on-another-game-modal-submit-join" class="modal-submit on-another-game-modal-submit-join" onclick="joinSubmit('${game.id}')">Join</div>
                        </div>
                    </dobble:modal> 
                </c:forEach>
            </div>
          </div>
                            
<script src="/resources/js/gameAndTournamentListActions.js"></script>

</dobble:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="tournaments">
   
    <dobble:errorMessage/>
    
    <div class="game-list-header">
        <h2>Tournaments</h2>
        <a href="/tournaments/new">
            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                </span>
            New Tournament
        </a>
    </div>
    <div class="game-list">
        <div class="game-list-head">
            <div style="width:10%; text-indent: 15px" class="header-field">ID<span onclick="sortTable(0)" class="glyphicon glyphicon-sort"></span></div>
            <div style="width:20%" class="header-field">Num. Games<span onclick="sortTable(1)" class="glyphicon glyphicon-sort"></span></div>
            <div style="width:15%; text-align:center" class="header-field">Owner</div>
            <div style="width:15%; text-align:center" class="header-field"><span class="glyphicon glyphicon-lock"></span><span onclick="sortTable(3)" class="glyphicon glyphicon-sort"></span></div>
            <div style="width:20%; text-align:center" class="header-field"></div>
            <div style="width:20%; text-align: right; transform: translateX(-15px);" class="header-field">Num. Players<span onclick="sortTable(5)" class="glyphicon glyphicon-sort"></span></div>
        </div>
        <div class="game-list-body">
            <c:forEach items="${tournaments}" var="tournament" varStatus="status">
                <spring:url value="/tournaments/{tournamentId}" var="tournamentUrl">
                    <spring:param name="tournamentId" value="${tournament.id}"/>
                </spring:url>
                
                   
                <div class="body-line" onclick="join('${tournament.id}','${tournament.isPrivate()}', '${user.getCurrentTournament()==null}')">
                    <div style="width:10%; text-indent: 15px" class="body-field">
                        <c:out value="${tournament.id}"/> 
                    </div>
                    <div style="width:20%" class="body-field">
                        <c:out value="${numpartidas[status.index]}"/> 
                    </div>
                    <div style="width:20%; text-align:center" class="body-field">
                        <c:out value="${tournament.owner}"/>
                    </div>
                    <div style="width:15%; text-align:center" class="body-field">
                        <c:if test="${tournament.isPrivate()}">  
                            <span class="glyphicon glyphicon-lock private-game-lock"></span>
                        </c:if>
                    </div>
                    <div style="width:15%; text-align:center" class="body-field">
                        <a onmouseenter="enableJoin=false" onmouseleave="enableJoin=true" href="${tournamentUrl}">Details</a>
                    </div>
                    <div style="width:20%; text-align: right; transform: translateX(-15px);" class="body-field num-players">
                        <c:out value="${tournament.numUsers}/${tournament.maxPlayers}"/>
                    </div>
                </div>
                <form:form  id="${tournament.id}-form" action="${tournamentUrl}/join" method="POST" modelAttribute="accessCode">
                    <c:if test="${game.isPrivate() && !user.getCurrentGame().equals(game)}">  
                        <dobble:modal id="${tournament.id}-access-modal" className="access-modal">
                            <h1 class="access-modal-title">Access Code</h1>
                            <div class="form">
                                <input id="${tournament.id}-access-modal-input" name="accessCode"  class="modal-input" placeholder="Enter access code..."/>
                                <div id="${tournament.id}-access-modal-submit" class="modal-submit" onclick="checkIsOnAnotherGame('${game.id}','${user.getCurrentGame()!=null}')">Join</div>
                            </div>
                        </dobble:modal>
                    </c:if>
                </form:form>
                <dobble:modal id="${tournament.id}-on-another-game-modal" className="on-another-game-modal">
                    <h1 style="text-align: center;" class="modal-title">You are already in another game</h1>
                    <div class="form">
                        <div id="${tournament.id}-on-another-game-modal" class="modal-submit on-another-game-modal-submit-current" onclick="join('${user.getCurrentTournament().getId()}')">current</div>
                        <div id="${tournament.id}-on-another-game-modal" class="modal-submit on-another-game-modal-submit-join" onclick="join('${tournament.id}')">Join</div>
                    </div>
                </dobble:modal> 
            </c:forEach>
        </div>
      </div>

<script src="/resources/js/gameAndTournamentListActions.js" tableName="tournaments-table-body"></script>


</dobble:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
   
    <c:if test="${error!=null && error!=''}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-alert"></span>
            <label>
                ERROR:  
            </label>
            <c:out value="${error}"></c:out>
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
    <div style="height: 100%;">
    <table id="game-list" class="game-list" >
        <tr>
            <td style="border-radius: 20px;">
                
                <table id="game-list-head" class="game-list-head">
                <thead>
                    <tr>
                        <th style="width: 10%; transform: translateX(15px);">ID<span onclick="sortTable('games-list-body',0)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="width: 20%;">Gamemode<span onclick="sortTable('games-list-body',1)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="width: 15%; text-align: center;">Owner</th>
                        <th style="width: 15%; text-align: center;"><span class="glyphicon glyphicon-lock"></span>
                            <span onclick="sortTable('games-list-body',3)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="width: 10%;"></th>
                        <th style="width:20%; right: 1%; text-align: right; transform: translateX(-5px);">Num. Players<span onclick="sortTable('games-list-body',5)" class="glyphicon glyphicon-sort"></span></th>
                    </tr>
                </thead>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div id="games-list-body-div">
                <table id="games-list-body" class="table game-list-body">
                <tbody>
            
                    <c:forEach items="${games}" var="game">
                        <spring:url value="/games/{gameId}" var="gameUrl">
                            <spring:param name="gameId" value="${game.id}"/>
                        </spring:url>
                        
                           
                            <tr onclick="join('${game.id}','${game.isPrivate() && !user.getCurrentGame().equals(game)}','${user.getCurrentGame()!=null}')">
                                
                                <td style="width: 10%; text-indent:15px;">
                                        <c:out value="${game.id}"/>
                                </td>
                                <td style="width: 20%;">
                                    <c:out value="${game.gamemode}"/>
                                </td>
                                <td style="width: 15%; text-align: center;">
                                    <c:out value="${game.owner}"/>
                                </td>
                                <td style="width: 15%; text-align: center;">
                                    <c:if test="${game.isPrivate()}">  
                                        <span class="glyphicon glyphicon-lock private-game-lock"></span>
                                    </c:if>
                                </td>
                                
                                <td style="width: 10%;">
                                    <a onmouseenter="enableJoin=false" onmouseleave="enableJoin=true" href="${gameUrl}">Details</a>
                                </td>
                                
                                <td style="width:20%; text-indent: 80%;" class="num-players">
                                    <c:out value="${game.numUsers}/${game.maxPlayers}"/>
                                </td>
                            </tr>
                            <form:form  id="${game.id}-form" action="${gameUrl}/join" method="POST" modelAttribute="accessCode">
                            <c:if test="${game.isPrivate() && !user.getCurrentGame().equals(game)}">  
                                <dobble:modal id="${game.id}-access-modal" className="access-modal">
                                    <h1 class="access-modal-title">Access Code</h1>
                                    <div class="form">
                                        <input id="${game.id}-access-modal-input" name="accessCode"  class="access-modal-input" placeholder="Enter access code...">
                                        <div id="${game.id}-access-modal-submit" class="access-modal-submit" onclick="checkIsOnAnotherGame('${game.id}','${user.getCurrentGame()!=null}')">Join</div>
                                    </div>
                                        
                                    </dobble:modal> 
                                </c:if>
                            </form:form>
                            <dobble:modal id="${game.id}-on-another-game-modal" className="access-modal">
                                    <h2 style="text-align: center;">You are already in another game</h2>
                                        <div class="form">
                                            <div id="${game.id}-access-modal-submit" class="access-modal-submit" onclick="join('${user.getCurrentGame().getId()}')">current</div>
                                            <div id="${game.id}-access-modal-submit" class="access-modal-submit" onclick="join('${game.id}')">Join</div>
                                        </div>
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
</div>   
<script src="/resources/js/gameAndTournamentListActions.js"></script>
</dobble:layout>

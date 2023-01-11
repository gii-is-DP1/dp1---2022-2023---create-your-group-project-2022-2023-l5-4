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
    <table id="games-list"  >
        <tr>
            <td>
                
                <table id="games-list-head" class="table">
                <thead>
                    <tr>
                        <th>ID<span onclick="sortTable('games-list-body',0)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -8%;">Gamemode<span onclick="sortTable('games-list-body',1)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -3%;">Owner</th>
                        <th style="position: relative; left: -3%;"><span class="glyphicon glyphicon-lock"></span>
                            <span onclick="sortTable('games-list-body',3)" class="glyphicon glyphicon-sort"></span></th>
                        <th></th>
                        <th style="position: relative; left: -1%; width:150px">Num. Players<span onclick="sortTable('games-list-body',5)" class="glyphicon glyphicon-sort"></span></th>
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
                        
                           
                            <tr onclick="join('${game.id}','${game.isPrivate() && !user.getCurrentGame().equals(game)}')">
                                
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
                                    <a onmouseenter="enableJoin=false" onmouseleave="enableJoin=true" href="${gameUrl}">Details</a>
                                </td>
                                
                                <td class="num-players" style="text-align:center;">
                                    <c:out value="${game.numUsers}/${game.maxPlayers}"/>
                                </td>
                            </tr>
                            <form:form  id="${game.id}-form" action="${gameUrl}/join" method="POST" modelAttribute="accessCode">
                            <c:if test="${game.isPrivate() && !user.getCurrentGame().equals(game)}">  
                                <dobble:modal id="${game.id}-access-modal" className="access-modal">
                                    <h2 style="text-align: center;">Access Code</h2>
                                        <div class="form">
                                            <input id="${game.id}-access-modal-input" name="accessCode"  class="access-modal-input" placeholder="Enter access code...">
                                            <input id="${game.id}-access-modal-submit" class="access-modal-submit" type="submit" value="Join">
                                        </div>
                                    </dobble:modal> 
                                </c:if>
                            </form:form>
                            
                            
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

<script src="/resources/js/gameAndTournamentListActions.js"></script>

</dobble:layout>

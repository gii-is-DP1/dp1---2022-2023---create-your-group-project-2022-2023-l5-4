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
                        <th>ID<span onclick="sortTable('tournaments-table-body',0)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -5%;">Num. Games<span onclick="sortTable('tournaments-table-body',1)" class="glyphicon glyphicon-sort"></span></th>
                        <th style="position: relative; left: -20%;">Owner</th>
                        <th style="position: relative; left: -9%;"><span class="glyphicon glyphicon-lock"></span>
                          <span onclick="sortTable('tournaments-table-body',3)" class="glyphicon glyphicon-sort"></span></th>
                        <th></th>
                        <th style="position: relative; left: 12%;">Num. Players<span onclick="sortTable('tournaments-table-body',5)" class="glyphicon glyphicon-sort"></span></th>
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
                            <tr onclick="join('${tournament.id}','${tournament.isPrivate()}')">
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
                                    <a onmouseenter="enableJoin=false" onmouseleave="enableJoin=true" href="${tournamentUrl}">Details</a>
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
<script src="/resources/js/gameAndTournamentListActions.js" tableName="tournaments-table-body"></script>


</dobble:layout>

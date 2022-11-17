<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <script>
        function goto(url){
         
            window.location=url
    }
    </script>
    <style>
      
        tbody > tr:hover > td {
            background-color: rgba(45, 168, 0, 0.508);
        }
    </style>
    <h2>Owners</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Gamemode</th>
            <th>Owner</th>
            <th>Users</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <spring:url value="/games/{gameId}" var="gameUrl">
                <spring:param name="gameId" value="${game.id}"/>
            </spring:url>
            
               
                <tr onclick="goto('${fn:escapeXml(gameUrl)}')">
                    
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
                        <c:out value="${game.numUsers}"/>
                    </td>
                    <td>
                        <a href="/">Play</a>
                    </td>
            
               </tr>
        
           
        </c:forEach>
        
        
        </tbody>
    </table>
    
</petclinic:layout>

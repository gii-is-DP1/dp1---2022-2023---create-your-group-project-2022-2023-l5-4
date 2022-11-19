<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    <script>
        function goto(url){
            window.location=url
    }
        async function join(gameUrl){
            console.log(gameUrl)
            await fetch(gameUrl,{
                method: "POST",
            })
   
        }
    </script>
    <style>
        

        tbody > tr:hover > td {
            background-color: #a77be591;
        }
        table .games-table {
            border-collapse: unset;
        }

        .game-list-header {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }
    </style>
    <div class="game-list-header">
        <h2>Games</h2>
        <a href="/games/new">
            <span class="glyphicon glyphicon-plus sucess" aria-hidden="true">
                </span>
            New Game
        </a>
    </div>
    <table id="games-table" class="table">
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
            
               
                <tr onclick="goto('${gameUrl}')">
                    
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
                    <td onclick="">
                        <a href="${gameUrl}/join">Join</a>
                           
                          
                    </td>
            
               </tr>
        
           
        </c:forEach>
        
        
        </tbody>
    </table>
    
</dobble:layout>

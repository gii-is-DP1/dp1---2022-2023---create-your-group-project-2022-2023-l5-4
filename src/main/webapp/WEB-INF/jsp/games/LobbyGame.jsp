<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<h2>Game players:</h2>
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">X</a>
		</div>
		</c:if>
	</div>
	<table class="table table-striped">
		<tr>
			<th>Users</th>
			<th>Actions</th>			
		</tr>
		 <c:forEach items="${users}" var="u">
			<tr>
				<td><c:out value="${u.user.username}"/></td>				
				<td>
					<a href="/users/edit/${u.user.username}"><span class="glyphicon glyphicon-pencil warning" aria-hden="true"></span></a>&nbsp;
					<a href="/games/${game.id}/play/delete/${u.user.username}"><span class="glyphicon glyphicon-trash alert" aria-hden="true"></a>&nbsp;
					<c:choose>
						<c:when test="${(loggedUser.getFriends().contains(u))}"> 
						<a href="/friends/remove/${u.user.username}"><span class="glyphicon glyphicon-minus-sign" aria-hden="true"></span></a> 
							</c:when>
							<c:otherwise>
								<a href="/friends/add/${u.user.username}"><span class="glyphicon glyphicon-plus-sign" aria-hden="true"></span></a> 
									</c:otherwise></c:choose>
					
						
				</td>
			</tr>
		</c:forEach>		
	</table>
	<p>
		<a href="http://localhost:8080/games/${game.id}/play">
			<button class="w3-button w3-purple">Start Game</button>
		</a>
	</p>
    
</petclinic:layout>
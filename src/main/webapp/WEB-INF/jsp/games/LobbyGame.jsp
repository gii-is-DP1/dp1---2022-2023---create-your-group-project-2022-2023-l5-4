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
		 <c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.user.username}"/></td>				
				<td>
					<a href="/users/profile/${user.user.username}"><span class="glyphicon glyphicon-book warning" aria-hden="true"></span></a>&nbsp;
					<a href="/games/${game.id}/lobby/delete/${user.user.username}"><span class="glyphicon glyphicon-trash alert" aria-hden="true"></a>&nbsp;
					<a href="/friends/add/${user.user.username}"><span class="glyphicon glyphicon-plus-sign" aria-hden="true"></span></a> 		
				</td>
			</tr>
		</c:forEach>		
	</table>
	<p>
		<a href="/games/${game.id}/start">
			<button class="w3-button w3-purple">Start Game</button>
		</a>
	</p>
    
</petclinic:layout>
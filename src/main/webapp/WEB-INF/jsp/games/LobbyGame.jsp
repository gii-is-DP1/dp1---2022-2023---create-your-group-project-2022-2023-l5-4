<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<h2>Game players:</h2>
	<div class="container">
		<br />
		<c:if test="${error!=null && error!=''}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-alert"></span>
				<label>
					ERROR:  
				</label>
				<c:out value="${error}"></c:out>
			</div>
		</c:if>
	</div>
	<table class="table table-striped">
		<tr>
			<th>Users</th>
			<th>Actions</th>			
		</tr>
		 <c:forEach items="${gameUsers}" var="gameUser">
			<tr>
				<td><c:out value="${gameUser.user.username}"/></td>				
				<td>
					<a href="/users/profile/${gameUser.user.username}"><span class="glyphicon glyphicon-book warning" aria-hden="true"></span></a>&nbsp;
					<a href="/games/${game.id}/lobby/delete/${gameUser.user.username}"><span class="glyphicon glyphicon-trash alert" aria-hden="true"></a>&nbsp;
					<c:choose>
						<c:when test = "${loggedUser.getFriends().contains(user)}"> 
							<a href="/friends/remove/${gameUser.user.username}"><span class="glyphicon glyphicon-minus-sign" aria-hden="true"></span></a> 		
						</c:when>
						<c:otherwise>
							<a href="/friends/add/${gameUser.user.username}"><span class="glyphicon glyphicon-plus-sign" aria-hden="true"></span></a>
						</c:otherwise>
					</c:choose>
					</td>
			</tr>
		</c:forEach>		
	</table>
	<p>
		<c:if test="${game.getOwner().equals(user)}">

			<form:form action="/games/${game.id}/start" method="POST">
				<input type="submit" class="w3-button w3-purple" value="Start Game"/>
			</form:form>
		</c:if>
	</p>
    
</dobble:layout>
<script src="/resources/js/checkForUpdate.js" href="/games/${game.id}/play" timeout="1000" params="lastUpdatedAt=${game.updatedAt}"></script>
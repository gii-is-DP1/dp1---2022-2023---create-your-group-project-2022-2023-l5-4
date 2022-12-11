<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
	<h2>Cames:</h2>
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
				<td><c:out value="${u.username}"/></td>				
				<td>
					<a href="/users/edit/${u.username}"><span class="glyphicon glyphicon-pencil warning" aria-hden="true"></span></a>&nbsp;<a href="/games/${game.id}/play/delete/${u.username}"><span class="glyphicon glyphicon-trash alert" aria-hden="true"></a> </td>
			</tr>
		</c:forEach>		
	</table>
	
    
</petclinic:layout>
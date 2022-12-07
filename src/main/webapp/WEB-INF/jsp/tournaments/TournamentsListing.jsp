<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tournaments">
	<h2>Tournaments:</h2>
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">X</a>
		</div>
		</c:if>
	</div>
	<a href="/tournaments/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Create tournament</a>
	<table class="table table-striped">
		<tr>
			<th>Id</th>
			<th>Owner</th>
			<th>Users</th>
			<th>Num. Games</th>			
			<th>Actions</th>
		</tr>
		 <c:forEach items="${tournaments}" var="p">
			<tr>
				<td><c:out value="${p.id}"/></td>
				<td><c:out value="${p.owner}"/></td>				
				<td>
					<ul>
						<c:out value="${p.numUsers}/${p.maxPlayers}"/>
					</ul>
				</td>
				<td><c:out value="${p.numGames}"></c:out></td>
				<td>
					<a href="/tournaments/delete/${p.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
			</tr>
		</c:forEach>		
	</table>
    
</petclinic:layout>
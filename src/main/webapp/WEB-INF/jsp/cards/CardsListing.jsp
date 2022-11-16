<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>bootstrap
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Cards</title>
</head>
<body>
	<h2>Cards:</h2>
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">X</a>
		</div>
		</c:if>
	</div>
	<a href="/cards/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Create card</a>
	<table class="table table-striped">
		<tr>
			<th>Name</th>
			<th>Symbols</th>			
			<th>Actions</th>
		</tr>
		 <c:forEach items="${cards}" var="p">
			<tr>
				<td><c:out value="${p.id}"/></td>				
				<td>Card of:
					<ul>
					<c:forEach items="${p.symbols}" var="o">
						<li><c:out value="${o.name}"/></li>
					</c:forEach>
					</ul>
				</td>
				<td>
					<a href="/cards/edit/${p.id}"><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>&nbsp;<a href="/cards/delete/${p.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
			</tr>
		</c:forEach>		
	</table>
</body>
</html>
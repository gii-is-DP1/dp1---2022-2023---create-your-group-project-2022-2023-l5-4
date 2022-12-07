<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Edit Tournament</title>
</head>
<body>
	<h2>Edit Tournament</h2>
	<mvc:form modelAttribute="tournament">
		<table>
			<tr>
				<td>
					<mvc:label path="nGames"> Num. Games</mvc:label>
				</td>
				<td><mvc:input path="nGames"></mvc:input></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="maxPlayers"> Num. Players</mvc:label>
				</td>
				<td><mvc:input path="maxPlayers"></mvc:input></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="password"> Password</mvc:label>
				</td>
				<td><mvc:input path="password"></mvc:input></td>
			</tr>
			<tr>
				<td><a href="/tournaments" class="btn btn-secondary">Cancel</a></td>
				<td><input type="submit" value="Save" class="btn btn-primary"></td>
			</tr>
		</table>
		
	</mvc:form>
</body>
</html>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

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
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">X</a>
		</div>
		</c:if>
	</div>
	<c:if test="${isowner}">
		<table class="table table-striped">
			<tr>
				<td><h2>Edit Tournament:</h2></td>
				<td><dobble:slider id="isPrivateSlider" label=""></dobble:slider></td>
			</tr>
		</table>

		<div id="isPrivateBody" class="hidden">
			<mvc:form modelAttribute="tournament">
				<table class="table table-striped">
					<tr>
						<td><mvc:label path="owner">Owner:</mvc:label></td>
						<td>
							<mvc:select path="owner" multiple="multiple" size="3"> 
								<mvc:options items="${users}" itemValue="username" itemLabel="username"></mvc:options>
							</mvc:select>
						</td>
					</tr>
					<tr>
						<div class="control-group">
							<dobble:selectField label="Gamemode" name="gamemodes" names="${gamemodes}" size="3"/>
						</div>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Save" class="btn btn-primary"/></td>
					</tr>
				</table>
			</mvc:form>
		</div>
	</c:if>
	<div class="table-responsive">
		<table class="table table-striped">
		  <tr>
			<td colspan="2"><h2>Tournament players:</h2></td>
		  </tr>
		  <tr>
			<th>Username</th>
			<th>Actions</th>     
		  </tr>
		  <c:forEach items="${users}" var="u">
			<tr>
			  <td>
				<c:out value="${u.username}"/>
				<c:if test="${u.username==tournament.owner}">&nbsp;<span class="glyphicon glyphicon-user warning" aria-hidden="true"></span></c:if>
			  </td>  
			  <td>
				<a href="/statistics/achievements/byUser/${u.username}"><span class="glyphicon glyphicon-book warning" aria-hidden="true"></span></a>&nbsp;
				<c:if test="${isowner}">  
				  <a href="/tournaments/${tournament.id}/lobby/delete/${u.username}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a>&nbsp;
				</c:if>
				<a href="/friends/add/${u.username}"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></a>
			  </td>
			</tr>
		  </c:forEach>    
		</table>
	  </div>
	<p>
		<c:if test="${isowner}">
			<table class="table table-striped">  
		<tr>
			<th>	
				<a href="/tournaments/${tournament.id}/play">
					<button class="w3-button w3-purple">Start Game in Tournament</button>
				</a>
			</th>
		</tr>		
	</table>
	</c:if>
	</p>
	<nav></nav>	
</dobble:layout>
<style>
    .hidden {
        display: none;
		visibility:hidden;
    }

	#isPrivateBody {
        display: none;
    }
	
</style>
<script src="/resources/js/handleIsPrivateSlider.js"></script>
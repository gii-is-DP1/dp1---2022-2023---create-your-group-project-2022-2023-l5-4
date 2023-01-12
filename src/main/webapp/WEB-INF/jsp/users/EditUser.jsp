<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="users">

	
	<form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
	<h2>Edit User: <c:out value="${user.username}"/></h2>
	<div class="form-group has-feedback">
            <dobble:inputField label="Password" name="password"/>
			<dobble:inputField label="Email" name="email"/>
		</div>
		<tr>
			<td><a href="/users" class="btn btn-secondary">Cancel</a></td>
			<td><input type="submit" value="Save" class="btn btn-primary"/></td>
		</tr>
	</form:form>
</dobble:layout>
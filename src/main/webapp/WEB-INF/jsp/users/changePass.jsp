<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users/editpass">

		<h2>Edit password:</h2>
	<form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Username" name="username" disabled/>
            <petclinic:inputField label="Password" name="password" value=""/>
            <petclinic:inputField label="Confirmar Password" name="password2" value=""/>
		</div>
		<tr>
			<td><a href="/users" class="btn btn-secondary">Cancel</a></td>
			<td><input type="submit" value="Save" class="btn btn-primary"/></td>
		</tr>
	</form:form>
</petclinic:layout>
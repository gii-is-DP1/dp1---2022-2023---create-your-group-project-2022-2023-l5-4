<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tournaments">
    <h2>Tournaments</h2>
	<a href="/tournaments/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Create Tournament</a>

    <table id="tourTable" class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tournaments.TournamentListing}" var="tour">
            <tr>
                <td>
                    <c:out value="${tour.id}"/>
					<c:out value="${tour.Description}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>

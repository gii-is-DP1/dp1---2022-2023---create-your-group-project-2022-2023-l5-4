<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="tournaments">

    <h2>Tournament Information</h2>
    <th><b>Tournamentmode: </b></th>

    <table class="table table-striped">
        <tr>
            <c:forEach items="${tournament.gamemodes}" var="p">
                <c:out value="${p.name}"/>,
            </c:forEach>
        </tr>
        <tr>
            <th>Owner</th>
            <td><c:out value="${tournament.owner}"/></td>
        </tr>
        <tr>
            <th>winner</th>
            <td><c:out value="${tournament.winner}"/></td>
        </tr>
        <tr>
            <th>users</th>
            <td><c:out value="${tournament.users}"/></td>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>

    

</dobble:layout>

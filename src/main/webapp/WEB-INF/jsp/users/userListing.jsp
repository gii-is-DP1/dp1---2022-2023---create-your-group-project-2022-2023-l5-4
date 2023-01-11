<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="users">


    <c:if test="${param.error!=null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-alert"></span>
            <label>
                ERROR:  
            </label>
            <c:out value="${param.error}"></c:out>
        </div>
    </c:if>

    <h2>User Information</h2>

    <table id="tournaments-table" class="table table-striped">
        <thead>
            <th>Username</th>
            <th>Email</th>
            <th>Delete player</th>
            <th>Edit player</th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
        <tr>
            <td>
                <c:out value="${user.username}"/>
            </td>
            <td>
                <c:out value="${user.email}"/>
            </td>
            <td>
                <spring:url value="/users/delete/{username}" var="userUrl">
                    <spring:param name="username" value="${user.username}"/>
                </spring:url>
                <a href="${fn:escapeXml(userUrl)}" class="btn btn-danger">Delete</a>
            </td>

            <td>
                <spring:url value="/users/edit/{username}" var="playerUrl">
                    <spring:param name="playerId" value="${player.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(playerUrl)}" class="btn btn-warning">Edit</a>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>

    <div class="row text-center">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
            <li class="page-item"><a class="page-link" href="/users?filterName=${filterName}&pageNumber=${previousPageNumber}">Previous Page</a></li>
            <li class="page-item"><a class="page-link" href="/users?filterName=${filterName}&pageNumber=${nextPageNumber}">Next Page</a></li>
            </ul>
        </nav>
    </div>

</dobble:layout>


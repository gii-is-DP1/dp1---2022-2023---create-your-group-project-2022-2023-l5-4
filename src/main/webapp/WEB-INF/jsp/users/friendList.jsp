<%@ page import="org.springframework.samples.dobble.user.User" %>
<%@ page import="org.springframework.samples.dobble.game.Game" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="nt4h" tagdir="/WEB-INF/tags" %>

<nt4h:layout pageName="friends">
    <h2>Friends</h2>

    <table id="friends" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Username</th>
            <th style="width: 200px">Actions</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friendsList}" var="localUser">
            <tr>
                <td><c:out value="${localUser.username}"/></td>
                <td>
					<a href="/friends/remove/${localUser.username}"><span class="glyphicon glyphicon-minus-sign" aria-hden="true"></span></a> 
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${page > 0}">
            <spring:url value="/friends?page={previous}" var="previous">
                <spring:param name="previous" value="${page-1}"/>
            </spring:url>
            <a href="${fn:escapeXml(previous)}" class="btn">Previous</a>
        </c:if>
        <c:if test="${isNext}">
            <spring:url value="/friends?page={next}" var="next">
                <spring:param name="next" value="${page+1}"/>
            </spring:url>
            <a href="${fn:escapeXml(next)}" class="btn">Next</a>
        </c:if>
</nt4h:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="achievements">
    <h2>Achievements</h2>
    <h4 class="score">TOTAL SCORE: <c:out value="${score}"/></h4>
    <progress class="ml-auto" value="${score}" max="10000"></progress>
    <h4 class="victories">VICTORIES: <c:out value="${victories}"/></h4>

    <ul class="list-group">
        <c:forEach items="${achievements}" var="achievement">
            <li class="list-group-item d-flex justify-content-between align-items-center">               
                <c:out value="${achievement.name}"/>
                <c:if test="${achievement.badgeImage != ''}">
                    <img class="ml-auto" src="${achievement.badgeImage}" width="50px" alt="Achievement badge"/> 
                </c:if>
            </li>                                
        </c:forEach>
    </ul>    
    <a class="btn btn-primary" href="/statistics/achievements">Available achievements</a>
        <style>
            .score {
                font-size: 30px;
                font-weight: bold;
            }

            progress {
                width: 50%;
                height: 20px;
                background-color: #ddd;
                border-radius: 10px;
            }
            
            progress::-webkit-progress-bar {
                background-color: #ddd;
                border-radius: 10px;
            }
            
            progress::-webkit-progress-value {
                background-color: #007bff;
                border-radius: 10px;
            }
    </style>
</dobble:layout>

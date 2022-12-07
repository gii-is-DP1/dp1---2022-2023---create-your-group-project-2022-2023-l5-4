<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="tournaments">
    
    <style>
        .number-players-select select{
            width: max-content;
            height: auto;
        }
        
    </style>
    <form:form modelAttribute="tournament" class="form-horizontal" id="add-game-form">
        <div class="form-group has-feedback">
			
            <div class="number-players-select">
                <dobble:selectField label="Max. Number of Players" name="maxPlayers" names="${[6,5,4,3,2]}" size="1"/>
            </div>
			<div class="number-players-select">
                <dobble:selectField label="Max. Number of Players" name="numGames" names="${[10,9,8,7,6,5,4,3,2]}" size="1"/>
            </div>
            <div class="control-group">
                <dobble:inputField label="Password" name="password"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${tournament['new']}">
                        <button class="btn btn-default" type="submit">Add Game</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</dobble:layout>
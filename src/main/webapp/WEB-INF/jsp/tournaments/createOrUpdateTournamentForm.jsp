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
    <h2>
        <c:if test="${tournament['new']}">New </c:if> tournament
    </h2>
    <form:form modelAttribute="tournament" class="form-horizontal" id="add-tournament-form">
        <div class="form-group has-feedback">
            <div class="control-group">
                <dobble:selectField label="gamemodes" name="gamemodes" names="${gamemodes}" size="3"/>
            </div>
            <div class="number-players-select">
                <dobble:selectField label="Max. Number of Players" name="maxPlayers" names="${[6,5,4,3,2]}" size="1"/>
            </div>
           <dobble:slider id="isPrivateSlider" label="Set Private"></dobble:slider>
           <div id="isPrivateBody" style="visibility:hidden;">
            <div class="control-group">
                <dobble:inputField label="Access Code" name="accessCode"/>
            </div>
           </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${tournament['new']}">
                        <button class="btn btn-default" type="submit">Add tournament</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update tournament</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</dobble:layout>
<script src="/resources/js/handleIsPrivateSlider.js"></script>

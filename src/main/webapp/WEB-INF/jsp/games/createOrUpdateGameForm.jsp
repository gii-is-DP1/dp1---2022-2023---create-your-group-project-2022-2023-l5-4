<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<dobble:layout pageName="games">
    
    <style>
        .number-players-select select{
            width: max-content;
            height: auto;
        }
        
    </style>
    <h2>
        <c:if test="${game['new']}">New </c:if> Game
    </h2>
    <form:form modelAttribute="game" class="form-horizontal" id="add-game-form">
        <div class="form-group has-feedback">
            <div class="control-group">
                <dobble:selectField label="Gamemode" name="gamemode" names="${gamemodes}" size="1"/>
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
                    <c:when test="${game['new']}">
                        <button class="btn btn-default" type="submit">Add Game</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Game</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</dobble:layout>
<script>
    let isPrivate = false;

        const slider =  document.getElementById("isPrivateSlider")
        const isPrivateBody =  document.getElementById("isPrivateBody");
        slider.onchange = function (){
            console.log("SLID")
            isPrivate = (this.checked)? true : false;
            if (isPrivate) isPrivateBody.style.visibility="visible"
            else isPrivateBody.style.visibility="hidden"
        }
    

</script>

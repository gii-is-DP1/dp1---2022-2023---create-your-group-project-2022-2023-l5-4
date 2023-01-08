<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>

<%@ attribute name="id" required="false" rtexprvalue="true"
              description="tag id" %>

<%@ attribute name="symbol" type="java.lang.Object" required="true" rtexprvalue="true"
              description="symbol" %>
              
<%@ attribute name="user" type="java.lang.Object" required="false" rtexprvalue="true"
              description="user" %>

<%@ attribute name="cardsetName" required="false" rtexprvalue="true"
              description="" %>
<style>
.hoverableSymbol {
        cursor: pointer;
        border: 2px solid transparent;
        border-radius:100%;
        height:100%;
        width:100%;
        overflow:visible;
        
}
    .hoverableSymbol:hover {
        border-color:red;
    }
    .symbol input {
        width:100%;
        height:100%;
        object-fit:contain;
        margin:auto;
    }
</style>

<div id="${id}" name="${symbol.getVariant(1)}" class="symbol ${className}">
<form:form action="play/match" method="POST">
    <input type="hidden" name="symbol" value="${symbol}" />
    <input type="hidden" name="user" value="${user}" />

    <c:choose>
        <c:when test="${user==null}">
             <input type="image" disabled=true draggable=false src="/resources/images/symbols/original/${symbol.getVariant(1).getFileName()}"/>
        </c:when>
        <c:otherwise>
        <div class="hoverableSymbol">
            <input type="image" id="#symbolBtn" draggable=false src="/resources/images/symbols/original/${symbol.getVariant(1).getFileName()}"/>
        </div>
        </c:otherwise>
    </c:choose>
</form:form>
</div>

<script>
</script>
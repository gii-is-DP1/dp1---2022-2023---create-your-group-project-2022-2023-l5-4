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

<%@ attribute name="listing" type="java.lang.Object" required="false" rtexprvalue="true"
              description="" %>
<%@ attribute name="cardsetName" required="false" rtexprvalue="true"
              description="" %>
<style>
.symbol {
        cursor: pointer;
        border: 2px solid transparent;
        overflow:visible;
       
}
    .symbol:hover {
        border-color:red;
    }
    .symbol input, .symbol img {
        width:100%;
        height:100%;
        object-fit:contain;
    }
</style>

<div id="${id}" name="${symbol.getVariant(1)}" class="symbol ${className}" onMouseEnter="console.log('${symbol.getVariant(1)}')">
<form:form action="play/match" method="POST">
    <input type="hidden" name="symbol" value="${symbol}" />
    <input type="hidden" name="user" value="${user}" />

    <c:choose>
        <c:when test="${listing==true}">
             <img draggable=false src="/resources/images/symbols/original/${symbol.getVariant(1).getFileName()}"/>
        </c:when>
        <c:otherwise>
            <input type="image" id="#symbolBtn" draggable=false src="/resources/images/symbols/original/${symbol.getVariant(1).getFileName()}"/>
        </c:otherwise>
    </c:choose>
</form:form>
</div>

<script>
</script>
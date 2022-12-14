<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>

<%@ attribute name="name" required="true" rtexprvalue="true"
              description="symbol name" %>

<%@ attribute name="symbol" type="java.lang.Object" required="true" rtexprvalue="true"
              description="symbol" %>


<%@ attribute name="cardsetName" required="false" rtexprvalue="true"
              description="symbol name" %>
<style>
.symbol {
        cursor: pointer;
        border: 2px solid transparent;
        overflow:visible;
       
}
    .symbol:hover {
        border-color:red;
    }
    .symbol input {
        width:100%;
        height:100%;
        object-fit:contain;
    }
</style>

<div class="symbol ${className}" onclick="console.log('${name}')">
<form:form action="/games/2/match" method="POST" modelAttribute="symbol">
    <input type="hidden" name="symbol" value="${symbol.getVariant(1).getFileName()}" />
    <input type="image" draggable=false src="/resources/images/symbols/original/${symbol.getVariant(1).getFileName()}"/>
</form:form>
</div>

<script>
</script>
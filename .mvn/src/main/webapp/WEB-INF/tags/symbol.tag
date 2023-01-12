<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>

<%@ attribute name="name" required="true" rtexprvalue="true"
              description="symbol name" %>


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

<div class="symbol ${className}">
<form:form action="/games/2/match" method="POST" modelAttribute="symbol">
    <input type="hidden" name="symbol" value="${name}" />
    <input type="image" draggable=false src="/resources/images/symbols/${cardsetName!=null ? cardsetName : 'original'}/${name}.png"/>
</form:form>
</div>

<script>
</script>
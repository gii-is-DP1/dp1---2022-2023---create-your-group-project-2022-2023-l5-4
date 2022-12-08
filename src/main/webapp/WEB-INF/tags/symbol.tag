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
    .symbol img {
        
        object-fit:contain;
    }
</style>

<div class="symbol ${className}" onclick="console.log('${name}')">
<img draggable=false src="/resources/images/symbols/${cardsetName!=null ? cardsetName : 'original'}/${name}.png">
</div>

<script>
</script>
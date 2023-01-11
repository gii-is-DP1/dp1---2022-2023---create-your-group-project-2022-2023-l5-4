<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>
<%@ attribute name="value" type="java.lang.Object" required="true" rtexprvalue="true"
              description="card object" %>
<%@ attribute name="centralDeck" type="java.lang.Object" required="false" rtexprvalue="true"
              description="" %>
<%@ attribute name="listing" type="java.lang.Object" required="false" rtexprvalue="true"
              description="" %>
                            
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <style>
    /* add your styles here */
    .dobble-card {
        display:block;
	overflow:hidden;
      aspect-ratio: 1;
      position: absolute;
      height:100%;
	  border-radius: 100%; /* make the card round */
	  box-shadow: 1px 2px 5px rgba(0, 0, 0, 0.5); /* add a shadow effect */
}
    
	.dobble-card-layout{
	top:50%;
	left:50%;
	translate:-50% -50%;
	border-radius: 100%;
	position: absolute;
	display:block;

	width:70%;
	height:70%;
		
	}
    .dobble-card-symbol-container {
        overflow:visible;
	display:block;
	  width: 42%;
   height:42%;
   line-height:60px;
   border-radius:100%;
   text-align:center;
	top:50%;
	left:50%;
	position: absolute;
    translate: -50% -50%;
    
    }
	
	.symbol{
      border-radius: 100%;
		position: absolute;
      display: block;
		
	}
	
	.symbol0-container {
	 width:50%;
   height:50%;
}

.symbol1-container {
   transform:rotate(51deg) translate(115%) rotate(-51deg);
}

.symbol2-container {
   transform:rotate(102deg) translate(115%) rotate(-102deg);
}

.symbol3-container {
   transform:rotate(153deg) translate(115%) rotate(-153deg);
}

.symbol4-container {
   transform:rotate(204deg) translate(115%) rotate(-204deg);
}

.symbol5-container {
   transform:rotate(255deg) translate(115%) rotate(-255deg);
}

.symbol6-container {
   transform:rotate(306deg) translate(115%) rotate(-306deg);
}

.symbol7-container {
   transform:rotate(357deg) translate(115%) rotate(-357deg);
}
img {object-fit: contain}

.listingId{
   display:block;
   font-size: 150px;
   font-weight: bolder;
   color: lightgray;
   opacity:0.5;
   text-align:center;
   vertical-align:middle;
   width: 100%;
   height:fit-content;
   top:-15px;
   vertical-align:middle;
   position: absolute;
   z-index:0;
   pointer-events: none
}
  </style>


<c:set value="${value.getCurrentCard()}" var="card"/>
<div class="${className}">
  <div class="dobble-card">
<c:if test="${listing==true}">
<div class="listingId">
   <c:out value="${card.getId()}"/>
</div>
</c:if>


  <div class="dobble-card-layout" id="dobble-card-layout-${card.getId()}">
    <div class="dobble-card-symbol-container symbol0-container">
      
          <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol0" symbol="${card.symbols[0]}" user="${listing || centralDeck ? null : value.user}"/>
      
    </div>
    <div class="dobble-card-symbol-container symbol1-container">
      
          <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol1" symbol="${card.symbols[1]}" user="${listing || centralDeck ? null : value.user}"/>
      
    </div>
	<div class="dobble-card-symbol-container symbol2-container">

             <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol2" symbol="${card.symbols[2]}" user="${listing || centralDeck ? null : value.user}"/>

    </div>
	<div class="dobble-card-symbol-container symbol3-container">
            
            <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol3" symbol="${card.symbols[3]}" user="${listing || centralDeck ? null : value.user}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol4-container">
            
            <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol4" symbol="${card.symbols[4]}" user="${listing || centralDeck ? null : value.user}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol5-container">
	        
                <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol5" symbol="${card.symbols[5]}" user="${listing || centralDeck ? null : value.user}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol6-container">
	        
               <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol6" symbol="${card.symbols[6]}" user="${listing || centralDeck ? null : value.user}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol7-container">
	        
            <dobble:symbol className="symbol-${card.getId()}" id="card-${card.getId()}-symbol7" symbol="${card.symbols[7]}" user="${listing || centralDeck ? null : value.user}"/>
            
    </div>
	</div>
  </div>
</div>
<script src="/resources/js/setUpCardAndSymbols.js" cardId="${card.getId()}" username="${listing || centralDeck ? null : value.user.username}" defer>

 
</script>


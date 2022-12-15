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
	
	.symbol0 {
	 width:50%;
   height:50%;
}

.symbol1 {
   transform:rotate(51deg) translate(115%) rotate(-51deg);
}

.symbol2 {
   transform:rotate(102deg) translate(115%) rotate(-102deg);
}

.symbol3 {
   transform:rotate(153deg) translate(115%) rotate(-153deg);
}

.symbol4 {
   transform:rotate(204deg) translate(115%) rotate(-204deg);
}

.symbol5 {
   transform:rotate(255deg) translate(115%) rotate(-255deg);
}

.symbol6 {
   transform:rotate(306deg) translate(115%) rotate(-306deg);
}

.symbol7 {
   transform:rotate(357deg) translate(115%) rotate(-357deg);
}
img {object-fit: contain}
  </style>


<c:set value="${value.getCurrentCard()}" var="card"/>
<div class="${className}">
  <div class="dobble-card">
  <div class="dobble-card-layout">
    <div class="dobble-card-symbol-container symbol0">
      
          <dobble:symbol id="symbol0" symbol="${card.symbols[0]}" listing="${listing}"/>
      
    </div>
    <div class="dobble-card-symbol-container symbol1">
      
          <dobble:symbol id="symbol1" symbol="${card.symbols[1]}" listing="${listing}"/>
      
    </div>
	<div class="dobble-card-symbol-container symbol2">

             <dobble:symbol id="symbol2" symbol="${card.symbols[2]}" listing="${listing}"/>

    </div>
	<div class="dobble-card-symbol-container symbol3">
            
            <dobble:symbol id="symbol3" symbol="${card.symbols[3]}" listing="${listing}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol4">
            
            <dobble:symbol id="symbol4" symbol="${card.symbols[4]}" listing="${listing}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol5">
	        
                <dobble:symbol id="symbol5" symbol="${card.symbols[5]}" listing="${listing}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol6">
	        
               <dobble:symbol id="symbol6" symbol="${card.symbols[6]}" listing="${listing}"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol7">
	        
            <dobble:symbol id="symbol7" symbol="${card.symbols[7]}" listing="${listing}"/>
            
    </div>
	</div>
	      
  </div>
</div>
<script>

  hashCode = (s) => {
         let hash = 0,
            i, chr;
         if (s.length === 0) return hash;
         for (i = 0; i < s.length; i++) {
            chr = s.charCodeAt(i);
            hash = ((hash << 5) - hash) + chr;
            hash |= 0; // Convert to 32bit integer
            }
         return hash
      }
   symbols = document.querySelectorAll(".symbol")
    
   symbols.forEach(symbol => {
      let i = symbol.attributes.id.value.replace("symbol","")
      let r = "${card.getId()}"
      let s = symbol.attributes.name.value
      let u = "${listing || centralDeck ? 'unnamed' : value.user.username}"
     

      let hashI = hashCode(i)
      let hashS = hashCode(s)
      let hashU = hashCode(u)
      let hashR = hashCode(""+(1/r))
      let k = Math.abs(hashS*hashR*hashU*hashI)%100


      const size = 60+40*k/100
      const x = 50-size/2 + (50-size/2) *(k/100)*(Math.round((k/100)) * 2 - 1)
      const y = 50-size/2 + (k/100)*Math.sqrt(Math.abs((100-size)^2-(x-50+size/2)^2))*(Math.round((k/100)) * 2 - 1)
      const angle = 360*(k/100)*(Math.round((k/100)) * 2 - 1)   
   
      symbol.style.height = size+"%"
      symbol.style.width = size+"%"
      symbol.style.left =  x+"%"
      symbol.style.top = y+"%"
      symbol.style.transform = "rotate("+angle+"deg)"
   });


    cards = document.querySelectorAll(".dobble-card-layout")

   cards.forEach(card => {
      let r = "${card.getId()}"
      let u = "${listing || centralDeck ? 'unnamed' : value.user.username}"
      let hashU = hashCode(u)
      let hashR = hashCode(""+(1/r))
      let k = Math.abs(hashR*hashU)%100
      const angle = 360*(k/100)
      card.style.transform = "rotate("+angle+"deg)"
   })


</script>


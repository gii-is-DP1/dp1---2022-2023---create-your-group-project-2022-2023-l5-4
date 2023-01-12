<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

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


<div class="${className}">
  <div class="dobble-card">
  <div class="dobble-card-layout">
    <div class="dobble-card-symbol-container symbol0">
      
          <dobble:symbol name="no-entry-sign" />
      
    </div>
    <div class="dobble-card-symbol-container symbol1">
      
          <dobble:symbol name="cheese"/>
      
    </div>
	<div class="dobble-card-symbol-container symbol2">

             <dobble:symbol name="yin-and-yang"/>

    </div>
	<div class="dobble-card-symbol-container symbol3">
            
            <dobble:symbol name="daisy-flower"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol4">
            
            <dobble:symbol name="light-bulb"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol5">
	        
                <dobble:symbol name="apple"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol6">
	        
               <dobble:symbol name="hammer"/>
            
    </div>
	<div class="dobble-card-symbol-container symbol7">
	        
            <dobble:symbol name="key"/>
            
    </div>
	</div>
	      
  </div>
</div>
<script>


   symbols = document.querySelectorAll(".symbol")
    
   symbols.forEach(symbol => {
      const size = 60+40*Math.random()
      const x = 50-size/2 + (50-size/2) *Math.random()*(Math.round(Math.random()) * 2 - 1)
      const y = 50-size/2 + Math.random()*Math.sqrt(Math.abs((100-size)^2-(x-50+size/2)^2))*(Math.round(Math.random()) * 2 - 1)
      const angle = 360*Math.random()
     
      symbol.style.height = size+"%"
      symbol.style.width = size+"%"
      symbol.style.left =  x+"%"
      symbol.style.top = y+"%"
      symbol.style.transform = "rotate("+angle+"deg)"
   });


    cards = document.querySelectorAll(".dobble-card-layout")

   cards.forEach(card => {
      const angle = 360*Math.random()
      card.style.transform = "rotate("+angle+"deg)"
   })


</script>


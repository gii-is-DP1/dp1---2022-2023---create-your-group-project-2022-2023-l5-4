<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<style>
	.cardListing{
		display: flex;
		flex-wrap: wrap;
		overflow: scroll;
		justify-content: space-around;
		align-items: center;
	}
	 .card {
		margin: 20px auto 20px auto;
		border-radius: 100%;
        display: block;
        position: relative;
        height: 200px;
        width: 200px;
    }

	.card:hover .listingId{
		opacity: 1;
	}
</style>


<dobble:htmlHeader/>

<body class="cardListing">
<c:forEach begin="1" end="55" step="1" var="card">
	<dobble:card value="${mockGame}" className="card" listing="true"/>
	<script>
		"${mockGame.nextCard()}"
	</script>
</c:forEach>
</body>


<script>
	
	allSymbolsDim = () =>{
		symbols.forEach(symbol=>{symbol.style.opacity="0.5"})	
	}

	allSymbolsHighlight = () => {
		symbols.forEach(symbol=>{symbol.style.opacity="1"})	
	}


	symbols = document.querySelectorAll(".symbol")
	 symbols.forEach(symbol=>{
		 symbol.onclick=(event)=>{
			allSymbolsDim()
			document.getElementsByName(symbol.getAttribute('name')).forEach( match => {
				match.style.opacity="1"
			}
				
			)
		}})

		document.getElementsByTagName("body")[0].onclick = (event)=>{
			if (event.target !== event.currentTarget) return
			allSymbolsHighlight()
		}

		
	
</script>
{
let hashCode = (s) => {
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

let cardId = document.currentScript.getAttribute("cardId")
let username = document.currentScript.getAttribute("username")
let symbols = document.querySelectorAll(".symbol-"+cardId)
symbols.forEach(symbol => {
 let htmlId = symbol.attributes.id.value.replace("symbol","")
 let s = symbol.attributes.name.value

 let hashA = hashCode(htmlId)
 let hashB = hashCode(s)
 let hashC = hashCode(""+(1/parseInt(cardId)))
 let k = Math.abs(hashA*hashB*hashC)%100


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


let card = document.getElementById("dobble-card-layout-"+cardId)

let cardRotate = () =>{
 let hashA = hashCode(username)
 let hashB = hashCode(""+(1/parseInt(cardId)))
 let k = Math.abs(hashA*hashB)%100
 const angle = 360*(k/100)
 card.style.transform = "rotate("+angle+"deg)"
}

cardRotate(card)

card.style.display="block"
}
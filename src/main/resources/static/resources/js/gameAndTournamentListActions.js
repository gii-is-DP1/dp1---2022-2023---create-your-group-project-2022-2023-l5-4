
function sortTable(name, n) {
  var table, rows, switching, i, x,x0,x1, y,y0,y1, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById(name);
  switching = true;
  dir = "asc"; 
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 0; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
        if ((n==1 || n==3) && x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          shouldSwitch= true;
          break;
        }

       
        if ((n==0) && parseInt(x.innerHTML) > parseInt(y.innerHTML)){
            shouldSwitch= true;
            break;
        }

        x0=parseInt(x.innerHTML.split("/")[0])
        x1=parseInt(x.innerHTML.split("/")[1])
        y0=parseInt(y.innerHTML.split("/")[0])
        y1=parseInt(y.innerHTML.split("/")[1])
        if (n==5 && (x1-x0 > y1-y0)){
            shouldSwitch= true;
            break;
        }
      } else if (dir == "desc") {
        if ((n==1 || n==3) && x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          shouldSwitch= true;
          break;
        }

       
        if ((n==0) && parseInt(x.innerHTML) < parseInt(y.innerHTML)){
            shouldSwitch= true;
            break;
        }
        x0=parseInt(x.innerHTML.split("/")[0])
        x1=parseInt(x.innerHTML.split("/")[1])
        y0=parseInt(y.innerHTML.split("/")[0])
        y1=parseInt(y.innerHTML.split("/")[1])
        if (n==5 && (x1-x0 < y1-y0)){
            shouldSwitch= true;
            break;
        }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;      
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}




function goto(url){
    window.location=url
}

function showAccessModal(bool){
    document.getElementById("access-modal").style.visibility=bool
}

let enableJoin = true
function join(id, isPrivate) {

if (!enableJoin) return
if(eval(isPrivate)) {
    const modal = document.getElementById(id+"-access-modal")
    modal.style.display="block"
} else {
    const form = document.getElementById(id+"-form").submit()
}      

}
document.addEventListener('keydown',function(e) { 
if (e.key === "Escape") { 
const modals = document.getElementsByClassName("modal")
for (let modal of modals){
modal.style.display = "none"

}
} 
})

const numPlayersIter = document.getElementsByClassName("num-players")
for (numPlayers of numPlayersIter){
const playersText = numPlayers.innerText.split("/")
const currentPlayers = playersText[0], maxPlayers = playersText[1]
if (currentPlayers >= maxPlayers) numPlayers.style.color="#ff3c38"
else if (currentPlayers == maxPlayers-1) numPlayers.style.color="#ffc921"
else numPlayers.style.color="#29cb3d"
numPlayers.style.fontWeight="bold"
}

    
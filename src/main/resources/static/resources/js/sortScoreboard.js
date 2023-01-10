

        function sortScoreboard() {
            var scores, i, switching, b, shouldSwitch;
            scores = document.getElementById("scores");
            switching = true;
            while (switching) {
              switching = false;
              b = scores.getElementsByTagName("p");
              for (i = 0; i < (b.length - 1); i++) {
                shouldSwitch = false;
                console.log(b[i].innerText)
                console.log(b[i].innerHTML.substring(
                  b[i].innerHTML.indexOf(":")+1))
                if (b[i].innerHTML.substring(
                  b[i].innerHTML.indexOf(":")+1) < 
                  b[i+1].innerHTML.substring(
                  b[i+1].innerHTML.indexOf(":")+1)) {
                  shouldSwitch = true;
                  break;
                }
              }
              if (shouldSwitch) {
                /* If a switch has been marked, make the switch
                and mark the switch as done: */
                b[i].parentNode.insertBefore(b[i + 1], b[i]);
                switching = true;
              }
            }
            scores.style.display="block"
          }
sortScoreboard()          
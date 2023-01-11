

        function sortScoreboard() {
            var scores, i, switching, b, shouldSwitch;
            scores = document.getElementById("scores");
            switching = true;
            while (switching) {
              switching = false;
              b = scores.getElementsByTagName("p");
              for (i = 0; i < (b.length - 1); i++) {
                shouldSwitch = false;
                var v1 = parseInt(b[i].innerHTML.substring(b[i].innerHTML.indexOf(":")+1)),
                    v2 = parseInt(b[i+1].innerHTML.substring(b[i+1].innerHTML.indexOf(":")+1))
                if ( v1 < v2 ) {
                  shouldSwitch = true;
                  break;
                }
              }
              if (shouldSwitch) {
                b[i].parentNode.insertBefore(b[i + 1], b[i]);
                switching = true;
              }
            }
            scores.style.display="block"
          }
sortScoreboard()          
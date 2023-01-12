let timer = document.getElementById("timer")
updateTimer = () => {
     t = timer.innerText.split(":")
     m = parseInt(t[0])
     s = parseInt(t[1])
     s++
     if(s==60) {
        s=0
        m++
     }
     s = s.toString().length == 2 ? s : `0${s}`
     timer.textContent = `${m}:${s}`
}   

window.setInterval(updateTimer,1000)
let params = document.currentScript.getAttribute("params")
let href = document.currentScript.getAttribute("href")
let timeout = parseInt(document.currentScript.getAttribute("timeout"))
function checkForUpdate() {
    $.ajax({
        type:"get",
        url: (href || window.location.href)+"/checkForUpdate?"+params,
        async: false,
        success: function(result) {
            if (result== "reload") window.location.reload()
        } 
    }
    );
}
window.setInterval(checkForUpdate, timeout || 500)
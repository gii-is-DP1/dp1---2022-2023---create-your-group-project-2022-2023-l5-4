let updatedAt = document.currentScript.getAttribute("updatedAt")
function checkForUpdate() {
    $.ajax({
        type:"get",
        url: window.location.href+"/checkForUpdate?lastUpdatedAt="+updatedAt,
        async: false,
        success: function(result) {
            if (result== "reload") window.location.reload()
        } 
    }
    );
}
window.setInterval(checkForUpdate, 500)
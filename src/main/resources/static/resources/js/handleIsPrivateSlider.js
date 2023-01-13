let isPrivate = false;

        const slider =  document.getElementById("isPrivateSlider")
        const isPrivateBody =  document.getElementById("isPrivateBody")
        const accessCodeInput = document.getElementById("accessCodeInput")
        slider.onchange = function (){
            isPrivate = (this.checked)? true : false
            if (isPrivate){
                isPrivateBody.style.display = 'block';
                accessCodeInput.value=""
            }
            else {
                isPrivateBody.style.display = 'none';
                accessCodeInput.value=null
            }
        }
    
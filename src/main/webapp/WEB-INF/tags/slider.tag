<%@ attribute name="id" required="true" rtexprvalue="true"
              description="id" %>
              <%@ attribute name="label" required="false" rtexprvalue="true"
              description="label" %>
<style>
.switch {
  position: relative;
  display: inline-block;
  width: 30px;
  height: 17px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 13px;
  width: 13px;
  left: 2px;
  bottom: 2px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}




input:checked + .slider:before {
  -webkit-transform: translateX(13px);
  -ms-transform: translateX(13px);
  transform: translateX(13px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 17px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
<div style="margin: 20px 0 20px 0;">
<label style="padding-top:0;" class="col-sm-2 control-label">
  ${label}
</label>
<label class="switch">
  <input type="checkbox" id="${id}">
  <span class="slider round"></span>
</label>
</div>


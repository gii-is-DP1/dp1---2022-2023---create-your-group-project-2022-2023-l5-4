<%@ attribute name="id" required="true" rtexprvalue="true"
              description="id" %>
<%@ attribute name="className" required="false" rtexprvalue="true"
              description="class" %>
<%@ attribute name="btnId" required="false" rtexprvalue="true"
              description="id for the element that will open the modal (declared outside this tag)" %>



<!-- Trigger/Open The Modal -->

<style>
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}</style>



<!-- The Modal -->
<div id="${id}" class="${className} modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close" id="${id}-close">&times;</span>
    <jsp:doBody/>
  </div>

</div>

<script>
// Get the modal




// Get the button that opens the modal


// Get the <span> element that closes the modal
var span = document.getElementById("${id}-close")
// When the user clicks on the button, open the modal


try{
    var btn = document.getElementById("${btnId}");
btn.onclick = function() {
  document.getElementById("${id}").style.display = "block";
}
}catch {}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  document.getElementById("${id}").style.display = "none";
}
var div=document.getElementById("${id}");
// When the user clicks anywhere outside of the modal, close it
div.onclick = function(event) {
  if (event.target == document.getElementById("${id}")) {
     document.getElementById("${id}").style.display = "none";
  }
}




</script>
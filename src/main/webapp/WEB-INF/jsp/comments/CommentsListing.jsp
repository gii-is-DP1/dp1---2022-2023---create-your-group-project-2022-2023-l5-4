<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

	<!-- Botón para abrir la ventana modal -->
	  
	  <style>
		.chat-button {
			position:relative;
			left:80%;
			top:100%;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #fff;
			background-color: #6d1bb9;
			border: none;
			border-radius: 0.25rem;
		}
	  
		.chat-button h4 {
			font-family: "Montserrat", sans-serif;
			}

			/* Hover effect for the button */
			.chat-button:hover {
			background-color: #ddd;
			color: #333;
			}

			/* Icon for the button */
			.chat-button i {
			color: #2196f3;
			}


	  </style>
	
	<!-- Contenido de la ventana modal -->
	<div class="modal fade" id="modalWindow" tabindex="-1" role="dialog" aria-labelledby="modalWindowLabel" aria-hidden="true">
				
				<div class="container">
					<br />
					<c:if test="${message != null}">
					<div class="alert alert-${messageType}">
						<c:out value="${message}"></c:out>
						<a href="#" class="close" data-dismiss="alert" aria-label="close">�</a>
					</div>
					</c:if>
				</div>
				<ul style="list-style: none; padding: 0;">
					<c:forEach items="${comments}" var="c">
					  <li style="display: flex; margin-bottom: 10px; background-color: #eee; border-radius: 5px;">
						<div>
						  <h4 style="margin: 0; font-family: sans-serif;">${c.user.username}</h4>
						  <p style="margin: 0; font-family: sans-serif;">${c.text}</p>
						  <small style="color: rgb(153, 153, 153); font-family: sans-serif;">${c.date}</small>
						</div>
					  </li>
					</c:forEach>
				</ul>
				<mvc:form modelAttribute="comment" action="/comments/${formid}/save">
					<table>
						<tr>
							<td><mvc:input path="text" /></td>
						</tr>
						<tr>
							<td><h8>&nbsp;</h8></td>
						</tr>
						<tr>
							<td colspan="1"><input style="font-family: sans-serif; color:#fff; background-color:  rgb(35, 38, 219); border: none; border-radius: 5px;" type="submit" value="Send Message"/></td>
							<td colspan="1"><button style="font-family: sans-serif; color:#fff; background-color: rgba(182, 26, 34, 0.877); border: none; border-radius: 5px;"" type="button" data-dismiss="modal">Close</button></td>
						</tr>
					</table>
				</mvc:form>
			</div>
			
	
	<!-- JavaScript para mostrar la ventana modal -->
	<script>
		$(function () {
		$('#modalWindow').modal('show');
		});
	</script>

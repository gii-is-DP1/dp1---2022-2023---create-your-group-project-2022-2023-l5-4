<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dobble" tagdir="/WEB-INF/tags" %>

<%@ attribute name="menuName" required="true" rtexprvalue="true"
              description="Name of the active menu: home, owners, vets or error" %>

<dobble:menu name="${menuName}"/>

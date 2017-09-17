<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<html>
	<body>
		Mensaje informativo: &nbsp;
		<c:choose>
			<c:when test="${exito}"> 
				Mensaje enviado con éxito.
			</c:when>
			<c:otherwise>	
				Error al enviar el mensaje.
			</c:otherwise>
		</c:choose>
	</body>
</html>
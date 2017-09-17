<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Contrato alta
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		
		<form id="frmCert" method="post" action="contratos_guardar.do">
		
			<jsp:include page="contratos_alta_cargar.jsp"/>
			
			<input type="button" value="Guardar" class="envCert" data-icon="check"/>
		
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
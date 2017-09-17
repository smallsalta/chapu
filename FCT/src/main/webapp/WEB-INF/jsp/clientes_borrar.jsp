<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Clientes borrar
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		¿Desea borrar al cliente ...?
		
		<a href="clientes_reborrar.do?oid=${oid}" data-role="button"> Sí </a>
		<a href="clientes_cargar.do?oid=${oid}" data-role="button"> No </a>
	</tiles:putAttribute>
		
</tiles:insertDefinition>
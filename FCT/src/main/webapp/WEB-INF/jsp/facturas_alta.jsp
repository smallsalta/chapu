<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Alta factura
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		
		<form method="post" action="facturas_guardar.do" id="fctfrm">
			<jsp:include page="facturas_alta_cargar.jsp"></jsp:include>
			
			<input type="button" value="Guardar" class="envFct" data-icon="check"/>
			
		</form>
		
		<script>
			factalta	= true;
			oidpago		= 1;
			oidcliente	= '${oidcliente}';
		</script>
		
	</tiles:putAttribute>

</tiles:insertDefinition>
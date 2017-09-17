<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Informe facturación  	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form method="post" action="informes_facturacion_resultados.do">
			<jsp:include page="facturas_buscar_informes.jsp"></jsp:include>
			
			<div>
				<input type="submit" id="" name="" data-role="button" value="Generar"/>
			</div>
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
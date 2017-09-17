<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Contrato info
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		
		<form id="frmCert" method="post" action="contratos_modificar.do">
		
			<jsp:include page="contratos_alta_cargar.jsp"/>
			
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<a target="blank" href="contratos_mail.do?oid=${contrato.oid}" data-role="button" data-icon="info">Email</a>
				</div>
				<div class="ui-block-b">
					<a target="blank" href="contratos_pdf.do?oid=${contrato.oid}" data-role="button" data-icon="grid">PDF</a>
				</div>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-b">
					<input type="button" value="Modificar" class="envCert" data-icon="refresh"/>
				</div>
				<div class="ui-block-c">
					<a href="contratos_borrar.do?oid=${contrato.oid}" data-role="button" data-icon="delete">Borrar</a>
				</div>
			</div>
			
			<input type="hidden" name="oid" value="${contrato.oid}"/>
		
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
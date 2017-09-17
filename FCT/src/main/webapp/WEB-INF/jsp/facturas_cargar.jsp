<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Factura info
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
	
		<form method="post" action="facturas_modificar.do" id="fctfrm">
		
			<jsp:include page="facturas_alta_cargar.jsp"></jsp:include>
		
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<a href="facturas_ver.do?oid=${factura.oid}" data-role="button" data-icon="grid">Ver</a>
				</div>
				<div class="ui-block-b">
					<a target="blank" href="facturas_pdf.do?oid=${factura.oid}" data-role="button" data-icon="grid">PDF</a>
				</div>
			</div>
			
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<input type="button" value="Copiar" class="envFctCopy" data-icon="refresh"/>
				</div>
				<div class="ui-block-b">
					<a target="blank" href="facturas_mail.do?oid=${factura.oid}" data-role="button" data-icon="info">Email</a>
				</div>
			</div>
			
			<div class="ui-grid-a">
				<div class="ui-block-b">
					<input type="button" value="Modificar" class="envFct" data-icon="refresh"/>
				</div>
				<div class="ui-block-c">
					<a href="facturas_borrar.do?oid=${factura.oid}" data-role="button" data-icon="delete">Borrar</a>
				</div>
			</div>
			
			<input type="hidden" name="oid" value="${factura.oid}"/>
		</form>
		
		<script>
			oidcliente	= '${factura.TClientes.oid}';
			oidempresa	= '${factura.TEmpresas.oid}';
			oidpago		= '${factura.TPagos.oid}';
			factalta	= false;
		</script>
		
	</tiles:putAttribute>

</tiles:insertDefinition>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page isELIgnored="false"%>
<% pageContext.setAttribute("newLineChar", "\\r\\n"); %>

<select name="oidcliente" class="oidcliente" data-native-menu="false" data-theme="c">
	<c:forEach items="${clientes}" var="c">
		<option value="${c.oid}"> ${c.nombre} ${c.apellidos} </option>
	</c:forEach>
</select>

<select name="oidempresa" class="oidempresa" data-native-menu="false" data-theme="c">
	<c:forEach items="${empresas}" var="e">
		<option value="${e.oid}"> ${e.descripcion} </option>
	</c:forEach>
</select>

<select name="oidpago" class="oidpago" data-native-menu="false" data-theme="c">
	<c:forEach items="${pagos}" var="e">
		<option value="${e.oid}"> ${e.descr} </option>
	</c:forEach>
</select>

<div class="ui-grid-b" id="factnum">
	<div class="ui-block-a">
		<input id="fecha" name="fecha" type="date" data-role="datebox" data-options='{"mode": "datebox"}' value='<fmt:formatDate value="${fecha}" pattern='yyyy-MM-dd'/>' />
	</div>
	<div class="ui-block-b">
		<input placeholder="Nº factura" type="text" class="numfct entero" name="numero" value="${factura.numero}" />
		
	</div>
	<div class="ui-block-c">
		<input class="entero" placeholder="IVA %" type="text" id="iva" name="iva" value='<fmt:formatNumber value="${factura.iva == 0 ? null : factura.iva}" pattern="#0.##"/>' />
	</div>
</div>

<div class="fila sortable">

	<c:if test="${empty factura.TLineasFacturas}">
		<c:import url="facturas_fila.jsp"/>
	</c:if>
	
	<c:forEach items="${factura.TLineasFacturas}" var="lf">
		<c:import url="facturas_fila.jsp">
			<c:param name="c">${lf.cantidad eq 0 ? null : lf.cantidad}</c:param>
			<c:param name="d">${fn:replace( lf.descripcion, newLine, '**' )}</c:param>
			<c:param name="p">${lf.precio eq 0 ? null : lf.precio}</c:param>
		</c:import>
	</c:forEach>

</div>

<div class="ui-grid-c btnfila" data-theme="e">
	<div class="ui-block-a">
		<!-- Subir -->
		<input type="button" value="." class="subir" data-icon="arrow-u" data-theme="e"/>
	</div>
	<div class="ui-block-b">
		<!-- Más -->
		<input type="button" class="mas" value="." data-icon="plus"/>
	</div>
	<div class="ui-block-c">
		<!-- Borrar -->
		<input type="button" value="." class="eliminar" data-icon="minus" data-theme="e"/>
	</div>
	<div class="ui-block-d">
		<!-- Bajar -->
		<input type="button" value="." class="bajar" data-icon="arrow-d" data-theme="e"/>
	</div>
</div>

<style>
	#factnum .ui-block-a { width: 52%; text-align: left; }
	#factnum .ui-block-b { width: 25%; }
	#factnum .ui-block-c { width: 23%; }
	
	.fila .ui-block-a { width: 21%; }
	.fila .ui-block-b { width: 58%; }
	.fila .ui-block-c { width: 21%; }
	
	.btnfila .ui-btn-text
	{ 
/*		font-size: 8px;*/
		font-size: 9px; 
		text-align: left;
	}
</style>

<script>
	fila 		= '<c:import url="facturas_fila.jsp"/>';
	factalta 	= false;
</script>
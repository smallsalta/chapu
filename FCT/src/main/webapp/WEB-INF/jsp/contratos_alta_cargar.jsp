<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<select name="oidclienteCert" class="oidclienteCert" data-native-menu="false" data-theme="c">
	<c:forEach items="${clientes}" var="c">
		<option value="${c.oid}" ${c.oid == oidcliente ? "selected" : "" }> ${c.nombre} ${c.apellidos} </option>
	</c:forEach>
</select>

<div class="ui-grid-a" id="factnum">
	<div class="ui-block-a">
		<input id="fechaCert" name="fechaCert" type="date" data-role="datebox" data-options='{"mode": "datebox"}'
				value='<fmt:formatDate value="${fecha}" pattern='yyyy-MM-dd'/>' />
	</div>
	<div class="ui-block-b">
		<input placeholder="Nº Contrato" type="text" value="${numero}" class="numCert entero" name="numCert" />
	</div>
</div>				

<div class="fila">

	<c:forEach items="${contrato.TLineasContratos}" var="lc">
		<c:import url="/contratos_fila.do">
			<c:param name="cantidad"> ${lc.cantidad} </c:param>
			<c:param name="placa"> ${lc.numeroPlaca} </c:param>
			<c:param name="prueba"> ${lc.TPruebas.oid} </c:param>
			<c:param name="agente"> ${lc.TAgentes.oid} </c:param>
			<c:param name="capacidad">
				<fmt:formatNumber pattern="#0.00" value="${lc.capacidad}"/>
			</c:param>
			<c:param name="fecha">
				<fmt:formatDate value="${lc.fecha}" pattern='yyyy-MM-dd'/>
			</c:param>					
			<c:param name="precio">
				<fmt:formatNumber pattern="#0.00" value="${lc.precio}"/>
			</c:param>
		</c:import>
	</c:forEach>
	
	<c:if test="${empty contrato.TLineasContratos}">
		<c:import url="/contratos_fila.do">
			<c:param name="fecha">
				<fmt:formatDate value="${fecha}" pattern='yyyy-MM-dd'/>
			</c:param>	
		</c:import>
	</c:if>

</div>

<div class="ui-btn-inner ui-li separador"></div>

<div>
	<textarea placeholder="Anexo" cols="40" rows="8" name="anexo" id="anexo" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">${contrato.anexo}</textarea>
</div>

<div class="ui-grid-c btnfila" data-theme="e">
	<div class="ui-block-a">
		<input type="button" value="." class="subirCert" data-icon="arrow-u" data-theme="e"/>
	</div>
	<div class="ui-block-b">
		<input type="button" class="masCert" value="." data-icon="plus" data-theme="e"/>
	</div>
	<div class="ui-block-b">
		<input type="button" value="." class="eliminarCert" data-icon="delete" data-theme="e"/>
	</div>
	<div class="ui-block-c">
		<input type="button" value="." class="bajarCert" data-icon="arrow-d" data-theme="e"/>
	</div>
</div>

<style>
	.btnfila .ui-btn-text
	{ 
		font-size: 9px; 
		text-align: left;
	}
	
	.separador
	{
		border-color: black;
	}
</style>
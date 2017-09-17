<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="fila">

	<div class="ui-btn-inner ui-li separador"></div>
	
	<div class="ui-grid-b">
		<div class="ui-block-a">
			<input value="${param.cantidad}" type="text" name="cantidadext" class="entero cantidad novacio" placeholder="Cantidad" />
		</div>
		<div class="ui-block-b">
			<input value="${param.placa}" type="text" name="placaext" class="placa novacio" placeholder="Nº placa extintor" />
		</div>
		<div class="ui-block-c">
			<input type="text" class="doble capacidad novacio" placeholder="Capacidad (kg)" name="capacidadext" 
					value='${param.capacidad}'/>
		</div>
	</div>

	<%-- La fecha puede venir por parámetro o por el model --%>	
	<c:set var="f"> ${param.fecha} </c:set>
	<c:if test="${param.fecha == null}">
		<c:set var="f">
			<fmt:formatDate value="${fecha}" pattern='yyyy-MM-dd'/>
		</c:set>
	</c:if>
	<%-- .................................................. --%>
	
	<div class="ui-grid-a">
		<div class="ui-block-a">
			<input class="minifecha novacio" name="fechaext" type="text"
				data-role="datebox" data-options='{"mode":"datebox"}' 
				placeholder="Fecha extintor"
				value='${f}'/>
		</div>
		<div class="ui-block-b">
			<input class="doble precio novacio" name="precioext" placeholder="Precio extintor"
					value='${param.precio}'/>
		</div>
	</div>
	
	<div class="ui-grid-a">
		<div class="ui-block-a">
			<select data-native-menu="false" data-theme="c" name="agentesext">
				<c:forEach items="${agentes}" var="a">
					<option value="${a.oid}" ${a.oid == param.agente ? "selected" : "" }>${a.descr}</option>
				</c:forEach>
			</select>
		</div>
		<div class="ui-block-b">
			<select data-native-menu="false" data-theme="c" name="pruebasext">
				<c:forEach items="${pruebas}" var="p">
					<option value="${p.oid}" ${p.oid == param.prueba ? "selected" : "" }>${p.descr}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
</div>
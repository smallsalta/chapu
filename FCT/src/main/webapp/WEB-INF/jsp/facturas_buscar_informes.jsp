<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<div class="ui-grid-a">
	<div class="ui-block-a">
		<input id="fini" name="fini" type="date" data-role="datebox" data-options='{"mode": "datebox"}' 
			value="<fmt:formatDate value='${fecha}' pattern='yyyy-MM-dd'/>"/>
	</div>
	<div class="ui-block-b">
		<input id="ffin" name="ffin" type="date" data-role="datebox" data-options='{"mode": "datebox"}' 
			value="<fmt:formatDate value='${fecha}' pattern='yyyy-MM-dd'/>"/>
	</div>
</div>

<div class="ui-grid-a">
	<div class="ui-block-a">
		<label data-theme="c">
			Clientes
			<input type="checkbox" name="checkcliente" id="checkcliente" data-theme="c" checked="checked" value="true"/>
		</label> 
	</div>
	<div class="ui-block-b">
		<select name="oidcliente" id="oidcliente" data-native-menu="false" data-theme="c">
			<c:forEach items="${clientes}" var="c">
				<option value="${c.oid}">${c.nombre} ${c.apellidos}</option>
			</c:forEach>
		</select> 
	</div>
</div>

<div class="ui-grid-a">
	<div class="ui-block-a">
		<label data-theme="c">
			Empresas
			<input type="checkbox" name="checkempresa" id="checkempresa" data-theme="c" checked="checked" value="true"/>
		</label> 
	</div>
	<div class="ui-block-b">
		 <select name="oidempresa" id="oidempresa" data-native-menu="false" data-theme="c">
			<c:forEach items="${empresas}" var="e">
				<option value="${e.oid}"> ${e.descripcion} </option>
			</c:forEach>
		</select>
	</div>
</div>
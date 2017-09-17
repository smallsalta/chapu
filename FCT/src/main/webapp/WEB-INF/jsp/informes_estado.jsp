<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Informe estado  	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form method="post" action="informes_estado_resultados.do">
			
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
			
			<select name="oidempresa" id="oidempresa" data-native-menu="false" data-theme="c">
				<c:forEach items="${empresas}" var="e">
					<option value="${e.oid}"> ${e.descripcion} </option>
				</c:forEach>
			</select>
			
			<input type="hidden" name="checkempresa" value="true"/>
			
			<div>
				<input type="submit" id="" name="" data-role="button" value="Generar"/>
			</div>

		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
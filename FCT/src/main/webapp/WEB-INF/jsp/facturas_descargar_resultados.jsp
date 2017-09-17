<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Descargar 	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form method="post" action="facturas_zip.do" target="_blank">
			<c:forEach items="${facturas}" var="f">
	 			<label>
        			<input type="checkbox" name="fctoid" value="${f.oid}" data-mini="true" data-theme="c"/> 
        			${f.numero} &emsp; ${f.TClientes.nombre} ${f.TClientes.apellidos}
    			</label>					
    		</c:forEach>
    		
    		<div class="ui-grid-a">
				<div class="ui-block-b">
					<input type="button" value="Todos" data-icon="plus" class="todo"/>
				</div>
				<div class="ui-block-c">
					<input type="button" value="Ninguno" data-icon="minus" class="ninguno"/>
				</div>
			</div>
		
			<input type="submit" value="Descargar" data-icon="check"/>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>

<script>
</script>

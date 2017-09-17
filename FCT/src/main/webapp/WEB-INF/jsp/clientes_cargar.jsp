<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Clientes info
	</tiles:putAttribute>
		
	<tiles:putAttribute name="body">
	
		<form method="post" action="clientes_modificar.do" id="frm">
		
			<jsp:include page="clientes_alta_cargar.jsp"></jsp:include>
			
	        <div class="ui-grid-a">
				<div class="ui-block-a">
					<input type="button" class="envusu" value="Modificar"/>
				</div>
				<div class="ui-block-b">
					<a href="clientes_borrar.do?oid=${fb.oid}" data-role="button">Borrar</a>
				</div>
			</div>
			
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<a href="facturas_alta.do?oid=${fb.oid}" data-role="button">Factura</a>
				</div>
				<div class="ui-block-b">
					<c:if test="${verBtnContrato}"> 
						<a href="contratos_alta.do?oid=${fb.oid}" data-role="button">Contrato</a>
					</c:if>
				</div>
			</div>
			
	        
	        <input type="hidden" name="oid" value="${fb.oid}"/>
	        
        </form>
        
	</tiles:putAttribute>
		
</tiles:insertDefinition>
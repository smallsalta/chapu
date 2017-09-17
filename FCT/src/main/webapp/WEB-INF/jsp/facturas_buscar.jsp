<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Buscar facturas 	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form method="post" action="facturas_buscar_resultados.do">
			<jsp:include page="facturas_buscar_informes.jsp"/>
			
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<label data-theme="c">
						Factura
						<input type="checkbox" name="checkfactura" id="checkfactura" data-theme="c" checked="checked" value="true"/>
					</label> 
				</div>
				<div class="ui-block-b">
					<input type="text" name="numfactura" id="numfactura" class="entero ui-input-text"/>
				</div>
			</div>
			
			<div>
				<input type="submit" id="" name="" data-role="button" value="Buscar"/>
			</div>
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
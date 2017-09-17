<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Clientes alta
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form method="post" action="clientes_guardar.do" id="frm">
		
			<jsp:include page="clientes_alta_cargar.jsp"/>
			
			<input type="button" class="envusu" value="Guardar"/>
        
        </form>
        
	</tiles:putAttribute>
		
</tiles:insertDefinition>
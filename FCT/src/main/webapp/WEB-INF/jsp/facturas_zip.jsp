<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Facturas descargar  	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<input type="submit" class="descargazip" name="" data-role="button" value="Descargar"/>
		
		<script>
			var zip = '${zip}';
		</script>
		
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
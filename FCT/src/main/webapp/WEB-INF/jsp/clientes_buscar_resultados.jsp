<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Clientes buscados
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<ul data-role="listview" data-filter="true" data-filter-placeholder="Clientes ..." data-inset="true">
		
			<c:forEach items="${clientes}" var="c">
				<li>
					<a href="clientes_cargar.do?oid=${c.oid}"> ${c.nombre} ${c.apellidos} </a>
				</li>
			</c:forEach>

		</ul>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>
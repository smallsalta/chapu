<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Bienvenido
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		&nbsp;
	</tiles:putAttribute>
		
</tiles:insertDefinition>
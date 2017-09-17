<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Informe resumen 	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">

		<table class="ui-responsive table-stroke" id="informe">
			<thead>
				<tr data-role="header" data-theme="b">
					<th id="col1">Número</th>
					<th id="col2">Cliente</th>
					<th id="col3">Cantidad</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resumen}" var="r">
					<tr>
						<td> 
							<a href="facturas_cargar.do?oid=${r.oid}" data-role="button" data-mini="true">
								<fmt:formatNumber pattern="000" value="${r.numero}"/>
							</a>
						</td>
						<td> ${r.cliente} </td>
						<td class="total"> 
							<fmt:formatNumber pattern="###,###,##0.00" value="${r.total}"/>
							&nbsp;
						</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
		
		<style>
			#informe
			{ 
				width: 100%; 
			}
			
			#col1, #col2, #col3
			{
				width: 33%;
			}
			
			.total
			{
				text-align: right;
			}
		</style>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>

<script>
</script>

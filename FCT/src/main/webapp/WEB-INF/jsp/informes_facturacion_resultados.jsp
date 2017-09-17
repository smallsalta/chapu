<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Informe facturación 	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<table id="informe" class="ui-responsive table-stroke">
			<thead>
				<tr data-role="header" data-theme="b">
					<th id="col1">Factura</th>
					<th id="col2">Base</th>
					<th id="col3">IVA</th>
					<th id="col4">Total</th>
				</tr>
			</thead>
			<tbody>
				<tr data-role="header" data-theme="e" id="total">
					<th>&nbsp;</th>
					<th> 
						<fmt:formatNumber pattern="###,###,##0.00" value="${total.base}"/> 
						&nbsp;
					</th>
					<th> 
						<fmt:formatNumber pattern="###,###,##0.00" value="${total.iva == 0 ? null : total.iva}"/>
						&nbsp;
					 </th>
					<th> 
						<fmt:formatNumber pattern="###,###,##0.00" value="${total.total}"/>
						&nbsp;
					</th>
				</tr>
				<c:forEach items="${facturas}" var="f">
					<tr>
						<td>  
							<a href="facturas_cargar.do?oid=${f.oid}" data-role="button" data-mini="true">
								<fmt:formatNumber pattern="000" value="${f.numero}"/>
							</a>
						</td>
						<td>  
							<fmt:formatNumber pattern="###,###,##0.00" value="${f.base}"/>
							&nbsp;
						</td>
						<td>  
							<fmt:formatNumber pattern="###,###,##0.00" value="${f.iva == 0 ? null : f.iva}"/>
							&nbsp;
						</td>
						<td> 
							<fmt:formatNumber pattern="###,###,##0.00" value="${f.total}"/>
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
			
			#col1, #col2, #col3, #col4
			{
				width: 25%;
			}
			
			td, #total
			{
				text-align: right;
			}
		</style>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>

<script>
</script>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		Informe estados 	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">

		<c:forEach items="${estado.total}" var="t">
			
			<h2 role="heading" class="ui-li ui-li-divider ui-bar-a">${t.key}</h2>
			
				<table id="informe" class="ui-responsive">
					<thead>
						<tr data-role="header" data-theme="b">
							<th id="col2">Base</th>
							<th id="col3">IVA</th>
							<th id="col4">Total</th>
						</tr>
					</thead>
					<tbody>
						<tr id="total">
							<th> 
								<fmt:formatNumber pattern="#,###,##0.00" value="${t.value.base}"/> 
								&nbsp;
							</th>
							<th> 
								<fmt:formatNumber pattern="#,###,##0.00" value="${t.value.iva == 0 ? null : t.value.iva}"/>
								&nbsp;
							 </th>
							<th> 
								<fmt:formatNumber pattern="#,###,##0.00" value="${t.value.total}"/>
								&nbsp;
							</th>
						</tr>
					</tbody>
				</table>
				
				<div data-role="collapsible-set" data-theme="c" data-content-theme="d" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
    				<div data-role="collapsible">
        				<h3>${ fn:length( estado.fact[ t.key ] ) } &nbsp; facturas</h3>
        				<c:forEach items="${ estado.fact[ t.key ] }" var="f">
					
							<a class="pdf" href="facturas_cargar.do?oid=${f.oid}" data-role="button" data-mini="true">
								<fmt:formatNumber pattern="000" value="${f.numero}"/>
							</a>
					
						</c:forEach>
						
    				</div>
				</div>								
				
				<hr/>
				
		</c:forEach>
		
		<style>
			#informe
			{ 
				width: 100%; 
			}
			
			#col2, #col3, #col4
			{
				width: 33%;
			}
			
			td, #total
			{
				text-align: center;
			}
			
			a.pdf
			{
				width: 30%;
				display: inline-block;
			}
		</style>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>

<script>
</script>

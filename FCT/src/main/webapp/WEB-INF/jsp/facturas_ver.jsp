<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page isELIgnored="false"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
							
<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Ver factura
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		
		${factura.TClientes.nombre} &nbsp; ${factura.TClientes.apellidos}
		<br/>
		${factura.TClientes.dni}
		<br/>
		${factura.TClientes.direccion}
		<br/>
		${factura.TClientes.cp} &nbsp; ${factura.TClientes.localidad} &nbsp; (${factura.TClientes.provincia})
		
		<hr/>
		
		<table>
			<tr>
				<td>Número:</td>
				<td>
					<fmt:formatNumber pattern="0000"> ${factura.numero} </fmt:formatNumber> 
				 </td>
			</tr>
			<tr>
				<td>Fecha:</td>
				<td> 
					<fmt:formatDate pattern='yyyy-MM-dd' value="${factura.fecha}"/> 
				</td>
			</tr>
			<tr>
				<td>IVA:</td>
				<td> 
					<fmt:formatNumber pattern="0.##"> ${factura.iva} </fmt:formatNumber> 
				</td>
			</tr>
		</table>
		
		<c:set var="total" value="0" scope="page"/>
		<table id="tver" class="ui-responsive table-stroke">
			<tbody>
				<c:forEach items="${factura.TLineasFacturas}" var="lf">
					<tr>
						<td class="cant">
							<fmt:formatNumber pattern="#,###,###.##" value="${lf.cantidad eq 0 ? null : lf.cantidad}"/>
							&nbsp; 
						</td>
						<td class="descr">
							${fn:replace(lf.descripcion, newLineChar, "<br/>")}
						</td>
						<td class="precio">
							<c:set var="total" value="${ total + lf.precio }"/>
							<fmt:formatNumber pattern="#,###,##0.00" value="${lf.precio eq 0 ? null : lf.precio}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<br/>
		
		<c:set var="iva" value="${ total*factura.iva/100 }"/>
		<table id="tres">
			<tbody>
				<tr>
					<td> 
						<fmt:formatNumber pattern="#,###,##0.00" value="${total}"/>  
					</td>
					<td> 
						<c:if test="${iva != 0}">
							<fmt:formatNumber pattern="#,###,##0.00" value="${iva}"/>   
						</c:if>
					</td>
					<td> 
						<fmt:formatNumber pattern="#,###,##0.00" value="${total+iva}"/>
					</td>
				</tr>
			</tbody>
		</table>
		
		<a href="facturas_cargar.do?oid=${factura.oid}" data-role="button">Volver</a>
		
		<style>
			#tver, #tres { width: 95%; margin: auto; }
			
			#tres td { text-align: center; }
			
			#tver .cant { width: 25%; text-align: right; }
			#tver .descr { width: 50%;  text-align: left;}
			#tver .precio { width: 25%; text-align: right; }
		</style>
		
	</tiles:putAttribute>

</tiles:insertDefinition>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">	
		Clientes buscar
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form method="post" action="clientes_buscar_resultados.do">
			<div>
				<input name="buscar" id="buscar" placeholder="Buscar ..." data-type="search" class="ui-input-text ui-body-c"/>
				
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<label data-theme="c">
							Clientes
							<input type="checkbox" name="checkcliente" id="checkcliente" data-theme="c" checked="checked" value="true"/>
						</label>
					</div>
					<div class="ui-block-b">
						<select name="oid" id="oid"	data-native-menu="false" data-theme="c">
							<c:forEach items="${clientes}" var="c">
								<option value="${c.oid}">${c.nombre} ${c.apellidos}</option>
							</c:forEach>
						</select>
					</div>
				</div>				
			</div>
			<input type="submit" id="" name="" data-role="button" value="Buscar"/>
		</form>

	</tiles:putAttribute>
		
</tiles:insertDefinition>
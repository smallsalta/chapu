<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul data-role="listview" data-ajax="false" data-inset="true"
	data-theme="d" class="ui-listview ui-listview-inset ui-corner-all">
	<li data-role="list-divider" role="heading"
		class="ui-li ui-li-divider ui-bar-b ui-first-child">Clientes</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="clientes_alta.do" class="ui-link-inherit">Crear</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="clientes_buscar.do" class="ui-link-inherit">Buscar</a>
	</li>
	
	<li data-role="list-divider" role="heading"
		class="ui-li ui-li-divider ui-bar-b ui-first-child">Facturas</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="facturas_alta.do" class="ui-link-inherit">Crear</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="facturas_buscar.do" class="ui-link-inherit">Buscar</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="facturas_descargar.do" class="ui-link-inherit">Descargar</a>
	</li>
	
	<li data-role="list-divider" role="heading"
		class="ui-li ui-li-divider ui-bar-b ui-first-child">Informes</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="informes_facturacion.do" class="ui-link-inherit">Facturación</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="informes_estado.do" class="ui-link-inherit">Estados</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="informes_recordatorio.do" class="ui-link-inherit">Recordatorio</a>
	</li>
	
	<sec:authorize access="hasRole('ROLE_CONTRATO')">
	<li data-role="list-divider" role="heading"
		class="ui-li ui-li-divider ui-bar-b ui-first-child">Contratos</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="contratos_alta.do" class="ui-link-inherit">Crear</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="contratos_buscar.do" class="ui-link-inherit">Buscar</a>
	</li>
	<li data-corners="false" data-shadow="false" data-iconshadow="true"
		data-wrapperels="div" data-icon="arrow-r" data-iconpos="right"
		data-theme="d"
		class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-d">
		<a href="contratos_descargar.do" class="ui-link-inherit">Descargar</a>
	</li>
	</sec:authorize>
</ul>
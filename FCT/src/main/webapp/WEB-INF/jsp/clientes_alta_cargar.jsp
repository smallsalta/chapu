<%@ page isELIgnored="false" %>

<div>
	NIF/CIF
	<input type="text" name="dni" id="dni" class="ui-input-text" value="${fb.dni}" />
</div>
<div>
	Nombre *
	<input type="text" name="nombre" id="nombre" class="ui-input-text" value="${fb.nombre}" />
</div>
<div>
	Apellidos 
	<input type="text" name="apellidos" id="apellidos" class="ui-input-text" value="${fb.apellidos}" />
</div>
<div>
	Dirección *
	<input type="text" name="direccion" id="direccion" class="ui-input-text" value="${fb.direccion}" />
</div>
<div>
	Localidad 
	<input type="text" name="localidad" id="localidad" class="ui-input-text" value="${fb.localidad}" />
</div>
<div>
	Provincia 
	<input type="text" name="provincia" id="provincia" class="ui-input-text" value="${fb.provincia}" />
</div>
<div>
	Código postal 
	<input type="text" name="cp" id="cp" class="entero ui-input-text" value="${fb.cp}" />
</div>
<div>
	Teléfono 1 
	<input type="text" name="telefono1" id="telefono1" class="entero ui-input-text" value="${fb.telefono1 == 0 ? null : fb.telefono1}" />
</div>
<div>
	Teléfono 2 
	<input type="text" name="telefono2" id="telefono2" class="entero ui-input-text" value="${fb.telefono2 == 0 ? null : fb.telefono2}" />
</div>
<div>
	Correo electrónico
	<input type="text" name="email" id="email" class="ui-input-text" value="${fb.email}" />
</div>

<div>
	Observaciones
	<textarea cols="40" rows="8" name="observaciones" id="observaciones" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">${fb.observaciones}</textarea>
</div>
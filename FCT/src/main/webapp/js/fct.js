var oidcliente	= null;
var oidempresa	= null;
var oidpago		= null;
var fila		= null;
var factalta	= null;
var obj			= null;

//////////////////////////////////////////////////////////
// INICIO
//////////////////////////////////////////////////////////

$(document).bind
(
	'pageinit',
	function(e) 
	{
		// En los select con muchos datos, se abre una página 
		// nueva ui-state=dialog esta página lanza el evento 
		// pageinit, el cual sólo debe ejecutarse 1 vez
		if( e.target.id == 'base' )
		{
			$(".entero").numeric( { negative:false, decimal:false } );
			
			initCli();
			initFct();
			initZip();
			initCer();
		}
	}
);

//////////////////////////////////////////////////////////
// COMUNES
//////////////////////////////////////////////////////////

jQuery.extend
(
	jQuery.mobile.datebox.prototype.options, 
	{ 'themeInput' : 'd' }
);

/**
 * Mensaje de alerta
 * @param txt	Texto a mostrar
 */
function alerta(txt)
{
	$("<div style='text-align:center;' class='ui-loader ui-overlay-shadow ui-body-e ui-corner-all'><h1> &nbsp; " + txt + " &nbsp; </h1></div>")
	.css({
			"display" : "block",
			"opacity" : 0.96,
			"left" : 0,
			"top" : $(window).height()/2 + 20,
			"width" : $(window).width()
		})
	.appendTo( $.mobile.pageContainer )
	.delay(800)
	.fadeOut( 1000, function(){ $(this).remove(); } );
}

/**
 * Verifica si cad es vacío
 * En caso de ser vacío, se lanza excepción
 * 
 * @param obj	Elemento HTML con problemas
 * @param cad	Cadena a comprobar
 */
function esVacio(obj, cad)
{	
	if(cad.length == 0)
	{
		throw "'" + obj + "' vacío";
	}
}

/**
 * Verifica si cad es un teléfono válido
 * 6xx xxx xxx
 * 7xx xxx xxx
 * 9xx xxx xxx
 * En caso de ser vacío, se lanza excepción
 * 
 * @param obj	Elemento HTML con problemas
 * @param cad	Cadena a comprobar
 */
function esTelefono(obj, cad)
{
//	esVacio(obj, cad);
	
	var RE = /^([679]{1}\d{8}){0,1}$/;
 
    if( !RE.test(cad) )
    {
    	throw "'" + obj + "' incorrecto";
    }
}

/**
 * Comprueba que el código postal es correcto
 * xxxxx
 * 
 * @param obj	Elemento HTML con problemas
 * @param cad	Cadena a comprobar
 */
function esCP(obj, cad)
{
	esVacio(obj, cad);
	
	var RE = /^\d{5}$/;
 
    if( !RE.test(cad) )
    {
    	throw "'" + obj + "' incorrecto";
    }
}

/**
 * Comprueba que el correo electrónico
 * 
 * @param obj	Elemento HTML con problemas
 * @param cad	Cadena a comprobar
 */
function esEmail(obj, cad) 
{
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	if( !re.test(cad) )
	{
		throw "'" + obj + "' incorrecto";
	}
}

/**
 * Código para borrar una línea de factura
 */
function borrarFila(e)
{
	var obj = e.target;
	
	while( $(obj).attr("class").indexOf("borrar") < 0 )
	{
		obj = $(obj).parent();
	}
	
	$(obj).parent().remove();
}

/**
 * Retorna: 
 * 1 = NIF ok, 
 * 2 = CIF ok, 
 * 3 = NIE ok, 
 * -1 = NIF error, 
 * -2 = CIF error, 
 * -3 = NIE error, 
 * 0 = ??? error
 */
function esDocumento(a)
{
	if( a != '' )
	{
		var res = valida_nif_cif_nie(a);
		
		switch(res)
		{
			case 0: 
				throw 'DNI erróneo'; 
				break;
			case -1: 
				throw 'NIF erróneo'; 
				break;
			case -2: 
				throw 'CIF erróneo'; 
				break;
			case -3: 
				throw 'NIE erróneo'; 
				break;
		}
	}
}

function valida_nif_cif_nie(a)
{
	var temp		= a.toUpperCase();
	var cadenadni 	= "TRWAGMYFPDXBNJZSQVHLCKE";
 
	if( temp!= '' )
	{
		//si no tiene un formato valido devuelve error
		if( ( !/^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$/.test( temp ) && !/^[T]{1}[A-Z0-9]{8}$/.test( temp ) ) && !/^[0-9]{8}[A-Z]{1}$/.test( temp ) )
		{
			return 0;
		}
 
		//comprobacion de NIFs estandar
		if( /^[0-9]{8}[A-Z]{1}$/.test( temp ) )
		{
			posicion		= a.substring( 8,0 ) % 23;
			letra 			= cadenadni.charAt( posicion );
			var letradni	= temp.charAt( 8 );
			
			if( letra == letradni )
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
 
 
		//algoritmo para comprobacion de codigos tipo CIF
		suma = parseInt(a.charAt(2))+parseInt(a.charAt(4))+parseInt(a.charAt(6));
 
		for( i=1; i<8; i+=2 )
		{
			temp1	= 2 * parseInt( a.charAt( i ) );
			temp1 	+= '';
			temp1 	= temp1.substring(0,1);
			temp2 	= 2 * parseInt( a.charAt( i ) );
			temp2 	+= '';
			temp2 	= temp2.substring( 1,2 );
			
			if( temp2 == '' )
			{
				temp2 = '0';
			}
 
			suma += ( parseInt( temp1 ) + parseInt( temp2 ) );
		}
		
		suma 	+= '';
		n		= 10 - parseInt( suma.substring( suma.length-1, suma.length ) );
 
		//comprobacion de NIFs especiales (se calculan como CIFs)
		if( /^[KLM]{1}/.test( temp ) )
		{
			if( a.charAt( 8 ) == String.fromCharCode( 64 + n ) )
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
 
		//comprobacion de CIFs
		if( /^[ABCDEFGHJNPQRSUVW]{1}/.test( temp ) )
		{
			temp = n + '';
			if( a.charAt( 8 ) == String.fromCharCode( 64 + n ) || a.charAt( 8 ) == parseInt( temp.substring( temp.length-1, temp.length ) ) )
			{
				return 2;
			}
			else
			{
				return -2;
			}
		}
 
		//comprobacion de NIEs
		//T
		if( /^[T]{1}[A-Z0-9]{8}$/.test( temp ) )
		{
			if( a.charAt( 8 ) == /^[T]{1}[A-Z0-9]{8}$/.test( temp ) )
			{
				return 3;
			}
			else
			{
				return -3;
			}
		}
		//XYZ
		if( /^[XYZ]{1}/.test( temp ) )
		{
			temp = temp.replace( 'X','0' )
			temp = temp.replace( 'Y','1' )
			temp = temp.replace( 'Z','2' )
			pos = temp.substring(0, 8) % 23;
 
			if( a.toUpperCase().charAt( 8 ) == cadenadni.substring( pos, pos + 1 ) )
			{
				return 3;
			}
			else
			{
				return -3;
			}
		}
	}
 
	return 0;
}

function objetoEvento(e) 
{
	obj = e.target;
}

function subir(objFilaAct)
{
	var objFilaPre = objFilaAct.prev(".fila");
	
	objFilaAct.after(objFilaPre);
	
	$('div[data-role="page"]').trigger('create');
}

function bajar(objFilaAct)
{
	var objFilaNxt = objFilaAct.next(".fila");
	
	objFilaNxt.after(objFilaAct);
	
	$('div[data-role="page"]').trigger('create');	
}

//////////////////////////////////////////////////////////
// CLIENTES
//////////////////////////////////////////////////////////

function initCli()
{
	/**
	 * Al enviar un cliente hacemos comprobaciones
	 */
	$(".envusu").click
	(
		function()
		{
			try
			{
				esDocumento( $("#dni").val() );
				esVacio( "Nombre", $("#nombre").val() );
				esVacio( "Dirección", $("#direccion").val() );
				
				if( $("#email").val() != '' )
				{
					esEmail( "Correo electrónico", $("#email").val() );
				}
				
				$("form#frm").submit();
			}
			catch(e)
			{
				alerta(e);
			}
		}
	);	
}

//////////////////////////////////////////////////////////
// FACTURAS
//////////////////////////////////////////////////////////

function initFct()
{
	$(".doble").numeric( { decimal:"," } );
	
	$(".subir").click
	( 
		function()
		{ 
			var objFilaAct = $(obj).parent().parent().parent();
			subir(objFilaAct);
		}
	);
	
	$(".bajar").click
	( 
		function()
		{ 
			var objFilaAct = $(obj).parent().parent().parent();
			bajar(objFilaAct);
		}
	);
	
	$(".eliminar").click
	( 
		function()
		{ 
			$(obj).parent().parent().parent().remove();			
			$('div[data-role="page"]').trigger('create');
		}
	);
	
	// Cuando pulsamos enviar/modificar
	$(".envFct").click
	(
		function()
		{
			enviarFactura();
		}
	);
	
	$(".envFctCopy").click
	(
		function()
		{
			$("#fctfrm").attr("action", "facturas_copiar.do");
			enviarFactura();
		}
	);

	/**
	 * Al pulsar cantidad se copia el objeto input cantidad
	 */
	$('.cantidad').click(objetoEvento);

	/**
	 * Añadir una fila al pulsar Más
	 */
	$(".mas").click 
	(
		function()
		{
			$('.fila').last().after(fila);
			$(".doble").numeric( { decimal:"," } );
			$('.cantidad').click(objetoEvento);
			
			$('div[data-role="page"]').trigger('create');
		}
	);

	/**
	 * Poder ordenar las líneas de factura
	 */
	$(".sortable").sortable();
	$(".sortable").disableSelection();
	
	/**
	 * Se carga una factura
	 * Se actualiza empresa, cliente y pago
	 */
	if( oidcliente != null )
	{
		$("select.oidcliente").last().val(oidcliente);
		$("select.oidcliente").last().selectmenu('refresh', true);
	}
	
	if( oidempresa != null )
	{	
		$("select.oidempresa").last().val(oidempresa);
		$("select.oidempresa").last().selectmenu('refresh', true);
	}
	
	if( oidpago != null )
	{	
		$("select.oidpago").last().val(oidpago);
		$("select.oidpago").last().selectmenu('refresh', true);
	}
	
	if( factalta )
	{
		$(".oidempresa").last().change
		(
			function()
			{
				jQuery.post
				( 
					"facturas_numero.do", 
					{ 
						oid: $(this).val() 
					}, 
					function (data, textStatus, jqXHR)
					{
						$(".numfct").last().val( data.numero );
					}, 
					"json"
				);
			}
		);
		
		$(".oidempresa").last().change();
	}
}

function enviarFactura()
{
	try
	{
		esVacio( "IVA", $("#iva").val() );
		esVacio( "Número", $(".numfct").last().val() );
		
		$("#fctfrm").submit();
	}
	catch(e)
	{
		alerta(e);
	}
}

//////////////////////////////////////////////////////////
// ZIP
//////////////////////////////////////////////////////////

function initZip()
{
	$(".descargazip").click 
	(
		function()
		{
			location.href = "download.do?filePath=" + zip;
		}
	);
	
	$(".todo").click 
	(
		function()
		{
			 $(':checkbox').prop('checked', true).checkboxradio("refresh");
		}
	);
	
	$(".ninguno").click 
	(
		function()
		{
			 $(':checkbox').prop('checked', false).checkboxradio("refresh");
		}
	);
}

//////////////////////////////////////////////////////////
// CERTIFICADOS
//////////////////////////////////////////////////////////

function initCer()
{
	$(".masCert").click 
	(
		function()
		{
			jQuery.post
			( 
				"contratos_fila.do", 
				function(data, textStatus, jqXHR)
				{
					$('.fila').last().after(data);
					$(".doble").numeric( { negative:false, decimal:"," } );
					$('.placa').click(objetoEvento);
					
					$('div[data-role="page"]').trigger('create');
				}
			);
		}
	);
	
	$(".placa").click(objetoEvento);
	
	$(".eliminarCert").click
	( 
		function()
		{ 
			$(obj).parent().parent().parent().parent().remove();
			$('div[data-role="page"]').trigger('create');
		}
	);
	
	$(".subirCert").click
	( 
		function()
		{ 
			var objFilaAct = $(obj).parent().parent().parent().parent();
			subir(objFilaAct);
		}
	);
	
	$(".bajarCert").click
	( 
		function()
		{ 
			var objFilaAct = $(obj).parent().parent().parent().parent();
			bajar(objFilaAct);
		}
	);
	
	$(".envCert").click 
	(
		function()
		{
			try
			{
				esVacio( "Nº contrato", $(".numCert").val() );
				
//				$(".placa.novacio").each
//				(
//					function()
//					{
//						esVacio( "Nº Placa extintor", $(this).val() );
//					}
//				);
			
//				$(".capacidad.novacio").each
//				(
//					function()
//					{
//						esVacio( "Capacidad extintor", $(this).val() );
//					}
//				);
				
				$(".precio.novacio").each
				(
					function()
					{
						esVacio( "Precio extintor", $(this).val() );
					}
				);
				
				$("#frmCert").submit();
			}
			catch(e)
			{
				alerta(e);
			}
		}
	);
}
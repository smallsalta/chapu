<!doctype html>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<html id="kkk">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		  
		<link rel="stylesheet" href="css/jquery.mobile-1.3.1.min.css" />
		
		<!--
		<link rel="stylesheet" href="css/jquery.mobile.min.css" />
		-->
		
		<link rel="stylesheet" href="css/parche.css" />
		<link rel="stylesheet" type="text/css" href="css/jqm-datebox.min.css" />
		
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="js/jquery-ui.js"></script>
 		<script src="js/jquery.mobile-1.3.1.min.js"></script>
		
		<!--
		<script src="js/jquery.min.js"></script>
  		<script src="js/jquery-ui.js"></script>
		<script src="js/jquery.mobile.min.js"></script>
		-->
		
		<script src="js/jqm-datebox.core.min.js"></script>
		<script src="js/jqm-datebox.mode.datebox.min.js"></script>
		<script src="js/jquery.mobile.datebox.js"></script>
		<script src="js/fct.js"></script>
		<script src="js/jquery.numeric.js"></script>
		
		<style>
			[data-role=page]{height: 100% !important; position:relative !important;}
			[data-role=footer]{bottom:0; position:absolute !important; top: auto !important; width:100%;} 
			.ui-block-b { display: table-cell; vertical-align: middle; }
			* { font-size: 12px; }
			h1 { font-size: 16px; }
			.ui-input-datebox { text-align: left; }
		</style>
		
		<script>
			/**
			 * Cargar script por página ...
			 * + Meter el script en <script class="trampa"/>
			 * + Poner ese script en <div data-role="page"/>
			 * + Comentar todo el código, entre comentario de bloque
			 */
			$(document).bind
		    (
		    	'pagechange',
		    	function() 
		    	{
				 	$(this).find('script.trampa').each
				  	(
						function() 
						{
							if( $(this).html() !== '' )
							{
				    			eval
				    			( 
				    				$(this).html().replace("/*", "").replace("*/", "") 
				    			);
							}
				  		}
					);
				}
		    );
		</script>
</head>
	<body>
		<div data-role="page" data-theme="b" id="base">

			<div data-role="header">
				<h1>:: FCT ::</h1>
			</div>
			
			<div data-role="content">
				<form action="j_spring_security_check" method="post" id="frm">
					<div data-demo-html="true">
             			<label for="j_username" class="ui-input-text">Usuario</label>
             			<div>
             				<input data-theme="b" type="text" id="j_username" name="j_username" value="" class="ui-input-text ui-body-c"/>
             			</div>
        			</div>
					<div data-demo-html="true">
             			<label for="j_password" class="ui-input-text">Contraseña</label>
             			<div>
             				<input data-theme="b" type="password" id="j_password" name="j_password" value="" class="ui-input-text ui-body-c"/>
             			</div>
        			</div>
        			<div data-demo-html="true">
        				<br/>
        				<input type="submit" id="enviar" value="Enviar" data-theme="b"/>
        			</div>
				</form>
						
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
					<script>
						alerta( "${SPRING_SECURITY_LAST_EXCEPTION.message}" );
					</script>
				</c:if>	
			</div>
			
			<div data-role="footer"> 
				&nbsp; Versión 2.3.0
			</div>
		
		</div>
	</body>
</html>
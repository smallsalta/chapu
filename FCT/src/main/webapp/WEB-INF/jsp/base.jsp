<!doctype html>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<html>
	<body>
		<div data-role="page" data-theme="b" id="base" data-dom-cache="false">

			<div data-role="header" data-fullscreen="true">
				<div class="ui-grid-b">
					<div class="ui-block-a">
						<a href="#mypanel" data-role="button" data-inline="true" data-icon="bars" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-icon-left ui-btn-up-b">
							<span class="ui-btn-inner">
								<span class="ui-btn-text">Menú</span>
								<span class="ui-icon ui-icon-home">&nbsp;</span>
							</span>
						</a>  
					</div>
					<div class="ui-block-b">
						<h5> <tiles:insertAttribute name="head"/> </h5>
					</div>	
					<div class="ui-block-c">
						<a href="j_spring_security_logout" data-role="button" data-inline="true" data-icon="bars" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-icon-left ui-btn-up-b">
							<span class="ui-btn-inner">
								<span class="ui-btn-text">Salir</span>
								<span class="ui-icon ui-icon-delete">&nbsp;</span>
							</span>
						</a>
					</div>
				</div>
			</div>
			
			<div data-role="content" id="contenido">
				<tiles:insertAttribute name="body"/>
			</div>
			
			<div data-role="panel" id="mypanel">
				<c:import url="/menu.do"></c:import>
			</div>
			
			<style>
				.ui-block-a, .ui-block-b, .ui-block-c { text-align: center; }
			</style>
		</div>	
		
	</body>
</html>
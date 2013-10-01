<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<title>Mapa de Servi&ccedil;os P&uacute;blicos</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" href="css/mapaservpub.css" type="text/css" media="all">
	<link rel="stylesheet" href="css/720_grid.css" type="text/css">
	<link rel="stylesheet" href="css/720_grid.css" type="text/css" media="screen and (min-width: 720px)">
	<link rel="stylesheet" href="css/986_grid.css" type="text/css" media="screen and (min-width: 986px)">
	<link rel="stylesheet" href="css/1236_grid.css" media="screen and (min-width: 1236px)" >
</head>
<body>
<div id="banner" class="banner-wrap">
	<div>
		<h1 id="logo">
			<a href="index.html">
				<img src="img/mapa-servicos-publicos-logo.gif" alt="Mapa de Servi&ccedil;os P&uacute;blicos" width="414" height="58" />
			</a>
		</h1>
	</div>
	<div id="formulario">
		<form method="post" action="index.html">
			<input type="text" id="txtEndereco" name="txtEndereco" class="ui-autocomplete-input" maxlength="80" title="Informe onde (rua, cidade) deseja localizar servico publico" placeholder="Informe onde (rua ou cidade) deseja localizar servico publico" ></input>
            <input type="button" id="btnEndereco" name="btnEndereco" value="Mostrar no Mapa" ></input>
		</form>
	</div>
</div>
<div style="display: inline; float:left; padding-right: 100px; padding-left: 50px;">
	<p><img src="img/PROCON.png" alt="Procon" />&nbsp;PROCON</p>
	<p><img src="img/CARTORIO.png" alt="Cartorio" />&nbsp;Cart&oacute;rios</p>
	<p><img src="img/ENS_BASICO.png" alt="Escola" />&nbsp;Escolas P&uacute;blicas</p>
	<p><img src="img/ENSINOSUPERIOR.png" alt="Faculdade" />&nbsp;Faculdades P&uacute;blicas</p>
	<p><img src="img/DELEGACIA.png" alt="Delegacia" />&nbsp;Delegacias de Pol&iacute;cia</p>
	<p><img src="img/RFB.png" alt="Delegacia" />&nbsp;Receita Federal</p>
	<p><img src="img/ASS_SOCIAL.png" alt="INSS" />&nbsp;Assist&ecirc;ncia Social</p>
	<p><img src="img/INSS.png" alt="INSS" />&nbsp;Previd&ecirc;ncia Social (INSS)</p>
	<p><img src="img/COMUTERAPEUTICA.png" alt="Terapia" />&nbsp;Comunidades Terap&ecirc;uticas</p>
	<p><img src="img/MTE.png" alt="Ministerio do Trabalho" />&nbsp;Minist&eacute;rio do Trabalho / SINE</p>
	<p><img src="img/UBS.png" alt="UBS" />&nbsp;Unidades B&aacute;sica de Sa&uacute;de (UBS)</p>
</div>
<div id="mapa"></div>
<hr />
<div class="row footer">
	<p>Quem Somos | Como Funciona | Contato</p>
</div>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
</body> 
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<title>Mapa de Serviços Públicos</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" href="./css/mapaservpub.css" type="text/css" media="all">
	<link rel="stylesheet" href="./css/720_grid.css" type="text/css">
	<link rel="stylesheet" href="./css/720_grid.css" type="text/css" media="screen and (min-width: 720px)">
	<link rel="stylesheet" href="./css/986_grid.css" type="text/css" media="screen and (min-width: 986px)">
	<link rel="stylesheet" href="./css/1236_grid.css" media="screen and (min-width: 1236px)" >
</head>
<body>
<div id="banner" class="banner-wrap">
	<div>
		<h1 id="logo">
			<a href="/home">
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
<div align="center" style="float: left; margin-left: 300px">
	<img src="img/CORREIOS.png" alt="Correios" />
	<div style="margin-top: -16px;" class="legendas">
		Agencia dos<div style="margin-top: -14px;">Correios</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/CARTORIO.png" alt="Cartórios" />
	<div style="margin-top: -16px;" class="legendas">
		Cartorio de<div style="margin-top: -14px;">Cartórios</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/ENS_BASICO.png" alt="Escolas de Ensino Básico" />	
	<div style="margin-top: -16px;" class="legendas">
		Ensino<div style="margin-top: -14px;">Básico</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/ENS_SUPERIOR.png" alt="Escolas de Ensino Superior" />
	<div style="margin-top: -16px;" class="legendas">
		Ensino<div style="margin-top: -14px;">Superior</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/DELEGACIA.png" alt="Delegacias de Polícia" />
	<div style="margin-top: -16px;" class="legendas">
		Delegacia<div style="margin-top: -14px;">de Policia</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/RFB.png" alt="Receita Federal do Brasil" />
	<div style="margin-top: -16px;" class="legendas">
		Receita<div style="margin-top: -14px;">Federal</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/ASS_SOCIAL.png" alt="Assistencia Social" />
	<div style="margin-top: -16px;" class="legendas">
		Assistência<div style="margin-top: -14px;">Social</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/INSS.png" alt="Previdência Social" />
	<div style="margin-top: -16px;" class="legendas">
		Previdência<div style="margin-top: -14px;">Social</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/COM_TERAP.png" alt="Comunidade Terapêutica" />
	<div style="margin-top: -16px;" class="legendas">
		Comunidade<div style="margin-top: -14px;">Terapeutica</div>
	</div>
</div>
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/MTE.png" alt="Postos de Trabalho" />
	<div style="margin-top: -16px;" class="legendas">
		Postos de<div style="margin-top: -14px;">Trabalho</div>
	</div>
</div>	
<div align="center" style="float: left; margin-left: 10px;">
	<img src="img/UBS.png" alt="Unidade Básica de Saude" />
	<div style="margin-top: -16px;" class="legendas">
		Unidade Básica<div style="margin-top: -14px;">de Saude</div>
	</div>
</div>

<div id="mapa"></div>
<div class="row footer">
	Quem Somos | Como Funciona | Contato
</div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
</body> 
</html>
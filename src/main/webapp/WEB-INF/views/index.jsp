<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<title>Mapa de Serviços Públicos Brasileiros</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<link rel="stylesheet" href="./css/mapaservpub.css" type="text/css" media="all">
</head>
<body>
<div id="cabecalho">
		<h1 id="logo">
			<a href="home" title="Mapa de Serviços Públicos">
				<img src="img/mapa-servicos-publicos-logo.gif" alt="Mapa de Serviços Públicos" width="414" height="58" />
			</a><br />Precisa encontrar algum serviço público?<br />
			Informe seu local e selecione o serviço que você precisa!
		</h1>
	<div id="formulario">
		<input type="text" id="txtEndereco" name="txtEndereco" class="ui-autocomplete-input" maxlength="80" title="Informe rua ou cidade para visualizar servicos publicos no mapa" placeholder="Informe rua ou cidade para visualizar servicos publicos no mapa" ></input>
        &nbsp;
        <input type="button" id="btnEndereco" name="btnEndereco" value="Mostrar no Mapa"></input>
	</div>
</div>
<br />
<div id="servicos">
	<input type="hidden" id="listaCategorias" value="${categorias}" />
	<c:forEach items="${categorias}" var="categoria">
		<div class="icone">
			<span class="legenda">
				<input type="checkbox" id="${categoria}" value="${categoria}" class="regular-checkbox" />
				<label for="${categoria}"></label>
				<!-- <label for="${categoria}"> -->
					<img src="img/${categoria}.png" alt="${categoria.descricao}" title="${categoria.descricao}" />
					${categoria.descricao}
				<!-- </label> -->
			</span>
		</div>
	</c:forEach>
</div>
<div id="mapa"></div>
<div id="info">Clique no ícone do serviço no mapa para obter informações de contato.</div>
<div id="rodape">
	<a href="https://docs.google.com/forms/d/1SHueEvgOTn0sWbqj027Mxb9qM-3QID5SbiTGo0LduNw/viewform" title="Clique e responda a pesquisa de opinião" target="_blank">
	<img src="img/opiniao-mapa-servico-publico.png"	width="32" height="30" alt="Clique e opine o que achou do Mapa de Serviços Públicos" />Achou este site útil? Clique aqui e opine!
	</a>
</div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/googleanalytics.js"></script>
<script type="text/javascript" src="js/gaevent.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
</body> 
</html>

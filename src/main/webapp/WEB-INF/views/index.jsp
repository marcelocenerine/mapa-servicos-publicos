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
	<div>
		<h1 id="logo">
			<a href="home" title="Mapa de Serviços Públicos">
				<img src="img/mapa-servicos-publicos-logo.gif" alt="Mapa de Serviços Públicos" width="414" height="58"
				onclick="_gaq.push(['_trackEvent', 'Home A', 'Logo', 'Logotipo']);" />
			</a><br />
			Informe endereço e selecione qual serviço público você precisa!
		</h1>
	</div>
	<div id="formulario">
		<input type="text" id="txtEndereco" name="txtEndereco" class="ui-autocomplete-input" maxlength="80" title="Informe onde (rua, cidade) deseja localizar serviço público" placeholder="Informe onde (rua ou cidade) deseja localizar servico publico"
		onclick="_gaq.push(['_trackEvent', 'Home A', 'Formulario', 'Caixa Texto Endereco']);" ></input>
        <input type="button" id="btnEndereco" name="btnEndereco" value="Mostrar no Mapa"
        onclick="_gaq.push(['_trackEvent', 'Home A', 'Formulario', 'Botao']);" ></input>
	</div>
</div>
<input type="hidden" id= "listaCategorias" value="${categorias}" />
<c:forEach items="${categorias}" var="categoria">
	<div class="icone">
		<img src="img/${categoria}.png" alt="${categoria.descricao}" />
		<div class="legenda">
			<input type="checkbox" id="${categoria}" value="${categoria}" checked
			onclick="_gaq.push(['_trackEvent', 'Home A', 'Checkbox', '${categoria}']);" />
			${categoria.descricao}
		</div>
	</div>
</c:forEach>

<div id="mapa"></div>

<div class="rodape">
	Quem Somos | Como Funciona | Contato
</div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
<script type="text/javascript" src="js/googleanalytics.js"></script>
</body> 
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en"> 
<head> 
	<title>Mapa de Serviços Públicos Brasileiros</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<link rel="stylesheet" href="./css/mapaservpub.css" type="text/css" media="all">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
<div id="cabecalho">
		<h1 id="logo">
			<a href="home" title="Mapa de Serviços Públicos">
				<img src="img/mapa-servicos-publicos-logo.gif" alt="Mapa de Serviços Públicos" width="414" height="58" />
			</a>
		</h1>
	<div id="formulario">
		Informe abaixo o local e selecione ao lado do mapa o serviço que você precisa:<br />
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
				<input type="checkbox" id="${categoria}" value="${categoria}" class="regular-checkbox" data-mini="true" />
				<label for="${categoria}"></label>
				<!-- <label for="${categoria}"> -->
					<img src="img/${categoria}.png" alt="${categoria.descricao}" title="${categoria.descricao}" />
					${categoria.descricao}
				<!-- </label> -->
			</span>
		</div>
	</c:forEach>
	<span class="info">Em breve, mais serviços públicos!</span>
</div>
<div id="mapa"></div>
<div class="info" style="margin-left: 25%;">Clique no ícone do serviço no mapa para obter informações de contato.</div>
<div id="links">
	<a id="sobrelink" style="cursor: pointer;" title="Clique e confira quem criou o Mapa de Serviços Públicos">Sobre</a> | 
	<a id="dadoslink" style="cursor: pointer;" title="Clique e confira a fonte de informações do Mapa de Serviços Públicos">Dados</a> | 
	<a id="contatolink" style="cursor: pointer;" title="Clique para contatar os desenvolvedores do Mapa de Serviços Públicos">Contato</a></div>
<div id="rodape">
	<!--  
	<a href="https://docs.google.com/forms/d/1SHueEvgOTn0sWbqj027Mxb9qM-3QID5SbiTGo0LduNw/viewform" title="Clique e responda a pesquisa de opinião" target="_blank">
	<img src="img/opiniao-mapa-servico-publico.png"	width="32" height="30" alt="Clique e opine o que achou do Mapa de Serviços Públicos" />Achou este site útil? Clique aqui e opine!
	</a>
	-->
</div>

<div id="sobre" title="Sobre o Mapa de Serviços Públicos" style="display:none;">
    <p>O projeto <em><strong>Mapa de Serviços Públicos</strong></em> foi desenvolvido em 2013, durante a disciplina de Engenharia de Software do Mestrado em Ciência da Computação da Universidade Federal de São Carlos (<strong>UFSCAR</strong>), campus Sorocaba/SP, pelos alunos:</p>
    <ul><li><a href="http://br.linkedin.com/in/marcelocenerino" target="_blank" title="Clique e confira perfil Linkedin de Marcelo Cenerino">Marcelo Cenerino</a></li>
    <li><a href="http://br.linkedin.com/in/nataschash" target="_blank" title="Clique e confira perfil Linkedin de Natascha Sava Hun">Natascha Sava Hun</a></li>
    <li>Rogério Colpani</li>
    <li>Tibério Camargo Guimarães</li></ul>
    <p>Sob a orientação do PhD Alexandre Álvaro, professor da disciplina Engenharia de Software.</p>
</div>
<div id="dados" title="Dados" style="display:none;">
	<p>O projeto <em><strong>Mapa de Serviços Públicos</strong></em> utiliza a plataforma <strong>Dados Abertos Governamentais</strong> 
	(<a href="http://dados.gov.br/tag/Equipamentos%20p%C3%BAblicos" target="_blank" title="Clique para abrir em nova aba/janela http://dados.gov.br">www.dados.gov.br</a>) como fonte dos endereços e contatos dos serviços públicos oferecidos.</p>
</div>
<div id="contato" title="Contato" style="display:none;">
	<p>Entre em contato com o grupo através do e-mail: nshgeek@gmail.com - Natascha Sava Hun</p>
</div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true"></script>

<script type="text/javascript" src="http://malsup.github.io/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/googleanalytics.js"></script>
<script type="text/javascript" src="js/gaevent.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
</body> 
</html>

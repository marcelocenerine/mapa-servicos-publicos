<!DOCTYPE html>
<html lang="en"> 
<head> 
	<title>Mapa de Serviços Públicos Brasileiros</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta name="description" content="Precisa encontrar um serviço público próximo a você? Saber horário de atendimento, telefone, e-mail? Localize no Mapa de Serviços Públicos Brasileiros!">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css" type="text/css" media="all">
	<link rel="stylesheet" href="css/mapaservpub.css" type="text/css" media="all">
</head>
<body>
<div id="mapa"></div>
<div id="menu">
			<span style="padding-left: 5px">&nbsp;</span>
			<a id="sobrelink" style="cursor: pointer;" title="Clique e confira quem criou o Mapa de Serviços Públicos">Sobre</a>
			<span style="padding-left: 30px">&nbsp;</span>
			<a id="comofuncionalink" style="cursor: pointer;" title="Clique e confira como funciona o Mapa de Serviços Públicos">Como Funciona</a>			
			<span style="padding-left: 30px">&nbsp;</span>
			<a id="dadoslink" style="cursor: pointer;" title="Clique e confira a fonte de informações do Mapa de Serviços Públicos">Dados</a>
			<span style="padding-left: 30px">&nbsp;</span>
			<a id="contatolink" style="cursor: pointer;" title="Clique para contatar os desenvolvedores do Mapa de Serviços Públicos">Contato</a>
			<span style="padding-left: 30px">&nbsp;</span>
</div>
<div id="cabecalho">
		<h1 id="logo">
			<a href="home" title="Mapa de Serviços Públicos">
				<img src="img/mapa-servicos-publicos-logo.gif" alt="Mapa de Serviços Públicos" width="414" height="58" />
			</a>
		</h1>		
	<div id="formulario">
		Informe abaixo o local e selecione ao lado do mapa o serviço que precisa:<br />
		<input type="text" id="txtEndereco" name="txtEndereco" class="ui-autocomplete-input" maxlength="80" title="Informe rua ou cidade para visualizar servicos publicos no mapa" placeholder="Informe rua ou cidade para visualizar servicos publicos no mapa" ></input>
        &nbsp;
        <input type="button" id="btnEndereco" name="btnEndereco" value="Mostrar no Mapa"></input>
	</div>
</div>
<div id="servicos">
	<#list categorias as categoria>
		<div class="icone">
			<span class="legenda">
				<input type="checkbox" id="${categoria}" value="${categoria}" class="regular-checkbox" data-mini="true" />
				<label for="${categoria}"></label>
					<img src="img/${categoria}.png" alt="${categoria.descricao}" title="${categoria.descricao}" />
					${categoria.descricao}
			</span>
		</div>
	</#list>
</div>
<div style="clear: both;"></div>
<div id="close" title="Clique para limpar rota">Fechar Rota&nbsp;&nbsp;&nbsp;X</div>
<div id="rotamsg">Arraste no mapa ponto A ao local de partida.</div>
<div id="rota"></div>
<div id="sobre" title="Sobre o Mapa de Serviços Públicos" style="display:none;">
	<p>O projeto <em><strong>Mapa de Serviços Públicos</strong></em> tem a finalidade de auxiliar na busca por serviços públicos próximos à localização do usuário, através da indicação em mapa de um ou mais serviços a partir de geolocalização.</p>
	<p>Oferece, ainda, opção de indicar trajeto (rota) até o local desejado e informações de contato, como telefone, email e horário de funcionamento (quando disponibilizados pelo estabelecimento).</p>
	<p>Atualmente, mais de dez serviços estão disponíveis com pretensão de, em breve, adição de novos serviços públicos e opção de avaliação pelos usuários.</p>
    <p>O projeto <em><strong>Mapa de Serviços Públicos</strong></em> foi desenvolvido em 2013, durante a disciplina de Engenharia de Software do Mestrado em Ciência da Computação da Universidade Federal de São Carlos (<strong>UFSCAR</strong>), campus Sorocaba/SP, pelos alunos:</p>
    <ul><li><a href="http://br.linkedin.com/in/marcelocenerino" target="_blank" title="Clique e confira perfil Linkedin de Marcelo Cenerino">Marcelo Cenerino</a></li>
    <li><a href="http://br.linkedin.com/in/nataschash" target="_blank" title="Clique e confira perfil Linkedin de Natascha Sava Hun">Natascha Sava Hun</a></li>
    <p>Sob a orientação do PhD Alexandre Álvaro, professor da disciplina Engenharia de Software.</p>
</div>
<div id="comofunciona" title="Como Funciona" style="display:none;">
	<p>&nbsp;<iframe width="560" height="315" src="//www.youtube.com/embed/qKQocFb-snU?rel=0" frameborder="0"></iframe></p>
</div>
<div id="dados" title="Dados" style="display:none;">
	<p>O projeto <em><strong>Mapa de Serviços Públicos</strong></em> utiliza a plataforma <strong>Dados Abertos Governamentais</strong> 
	(<a href="http://dados.gov.br/tag/Equipamentos%20p%C3%BAblicos" target="_blank" title="Clique para abrir em nova aba/janela http://dados.gov.br">www.dados.gov.br</a>) como fonte dos endereços e contatos dos serviços públicos oferecidos.</p>
</div>
<div id="contato" title="Contato" style="display:none;">
	<p>Entre em contato com o grupo através do e-mail: marcelocenerine@gmail.com</p>
</div>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAQftpBSaMVPXUWPimQ4QdcUb8foJgV6P4&sensor=true&language=pt-BR"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/mapaservpub.js"></script>
</body> 
</html>

ga('send', 'pageview', {
	  'page': '/homeB',
	  'title': 'Menu ao Lado do Mapa'
});

$('#logotipo').click(function() {
	  ga('send', 'event', 'Home B Logotipo', 'click');
});

$('#txtEndereco').click(function() {
	  ga('send', 'event', 'Home B Caixa de Texto Endereco', 'click');
});

$('#btnEndereco').click(function() {
	  ga('send', 'event', 'Home B - Botao Busca Endereco', 'click');
});

$('input:checkbox').click(function() {
	var selectcheckbox;
	if ($('#' + $(this).val()).is(':checked')) {
		selectcheckbox = " selecionado";
	} else {
		selectcheckbox = " removida selecao";
	}
	ga('send', 'event', 'Home B - Checkbox ' + $(this).val() + selectcheckbox, 'click');
});

$('#rodape').click(function() {
	  ga('send', 'event', 'Home B - Link Pesquisa de Opiniao', 'click');
});
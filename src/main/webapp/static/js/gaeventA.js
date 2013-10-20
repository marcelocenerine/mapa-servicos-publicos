ga('send', 'pageview', {
	  'page': '/home',
	  'title': 'Menu Acima do Mapa'
});

$('#logotipo').click(function() {
	  ga('send', 'event', 'Home A Logotipo', 'click');
});

$('#txtEndereco').click(function() {
	  ga('send', 'event', 'Home A Caixa de Texto Endereco', 'click');
});

$('#btnEndereco').click(function() {
	  ga('send', 'event', 'Home A - Botao Busca Endereco', 'click');
});

$('input:checkbox').click(function() {
	var selectcheckbox;
	if ($('#' + $(this).val()).is(':checked')) {
		selectcheckbox = " selecionado";
	} else {
		selectcheckbox = " removida selecao";
	}
	ga('send', 'event', 'Home A - Checkbox ' + $(this).val() + selectcheckbox, 'click');
});

$('#rodape').click(function() {
	  ga('send', 'event', 'Home A - Link Pesquisa de Opiniao', 'click');
});
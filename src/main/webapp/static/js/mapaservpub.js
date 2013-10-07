	var map;
	var marker;
	var geocoder;
	var markers = [];
	var servicos = 'CORREIOS, CARTORIO, ENS_BASICO, ENS_SUPERIOR, RFB, ASS_SOCIAL, INSS, COM_TERAP, SINE, UBS';
			  
	$(document).ready(function () {
	
	getLocation();
	
	//Busca geolocalizacao do usuario
	function getLocation() {
		var latdefault = -13.0000000;
		var longdefault = -47.000000;
		initialize(latdefault, longdefault, 4, 2, false);
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
				function (position) {
					initialize(position.coords.latitude, position.coords.longitude, 14, 1, true);
				},
				function (erro) {
					initialize(latdefault, longdefault, 4, 2, false);
				}
			);
		}
	}
	
	//Inicializacao do site
	function initialize(latitude, longitude, zoom, enderecodesc, pontosmapa) {
		var latlng = new google.maps.LatLng(latitude, longitude);
		var options = {
			zoom: zoom,
			center: latlng,
			panControl: true,
			streetViewControl: true,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('mapa'), options);
		
		geocoder = new google.maps.Geocoder();
		marker = new google.maps.Marker({
			map: map,
			draggable: false,
		});
		marker.setPosition(latlng);

		geocoder.geocode({'latLng': latlng}, function(results, status) {
		  if (status == google.maps.GeocoderStatus.OK) {
			if (results[enderecodesc]) {
					$("#txtEndereco").val(results[enderecodesc].formatted_address);
				}
			}
		});		
		
		if (pontosmapa == true) {
			setPontosMapa(latitude, longitude, servicos);
		}
	}
	
	//Sugere enderecos  eexibe no mapa o local clicado
	$("#txtEndereco").autocomplete({
		source: function (request, response) {
			geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
				response($.map(results, function (item) {
					return {
						label: item.formatted_address,
						value: item.formatted_address,
						latitude: item.geometry.location.lat(),
          				longitude: item.geometry.location.lng()
					};
				}));
			});
		},
		select: function (event, ui) {
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			marker.setPosition(location);
			map.setCenter(location);
			map.setZoom(14);
			setPontosMapa(ui.item.latitude, ui.item.longitude, servicos);
		}
	});
	
	
	//Exibe no mapa servicos publicos desejados
	function setPontosMapa(latitude, longitude, servicos) {
		limparMarcadores();
		$.getJSON('rest/api/servicos/lng/'+longitude+'/lat/'+latitude+'/categorias/'+servicos, function(pontos) {
			$.each(pontos, function(index, ponto) {
					var marker = new google.maps.Marker({
						position: new google.maps.LatLng(ponto.localizacao.coordenadas.latitude, ponto.localizacao.coordenadas.longitude),
						title: ponto.categoria.descricao,
						map: map,
						icon: 'img/' + ponto.categoria.tipo + '.png'
					});
					rastrearMarcador(marker);

					var infowindow = new google.maps.InfoWindow(), marker;
					google.maps.event.addListener(marker, 'click', (function(marker, i) {
						return function() {
							var msgbalao = "<p><strong>" + ponto.categoria.descricao + "</strong></p>";
							if (ponto.nome) {
								msgbalao += "<p>" + ponto.nome + "</p>";
							}
							if (ponto.atendimento) {
								msgbalao += "<p>Atendimento: " + ponto.atendimento + "</p>";
							}
							if (ponto.contato) {
								if (ponto.contato.telefones) {
									msgbalao += "<p>Telefones: " + ponto.contato.telefones + "</p>";
								}
								if (ponto.contato.email) {
									msgbalao += "<p>Email: " + ponto.contato.email; + "</p>"
								}
							}
							infowindow.setContent(msgbalao);
							infowindow.open(map, marker);
						};
					})(marker));
			});
		});	
	}
	
	function rastrearMarcador(marker) {
		markers.push(marker);
	}
	
	function limparMarcadores() {
		$(markers).each(function(i, e) { 
			e.setMap(null);
			}
		);
	}
	
});

	var map;
	var marker;
	var geocoder;	
			  
	$(document).ready(function () {
	
	getLocation();
	
	function getLocation() {
		var latdefault = -10.0000000;
		var longdefault = -48.000000;
		initialize(latdefault, longdefault, 4, 2);
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
				function (position) {
					initialize(position.coords.latitude, position.coords.longitude, 15, 1);
				},
				function (erro) {
					initialize(latdefault, longdefault, 4, 2);
				}
			);
		}
	}
	
	function initialize(latitude, longitude, zoom, enderecodesc) {
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

		geocoder.geocode({'latLng': latlng}, function(results, status) {
		  if (status == google.maps.GeocoderStatus.OK) {
			if (results[enderecodesc]) {
					$("#txtEndereco").val(results[enderecodesc].formatted_address);
				}
			}
		});		
		
		
		$.getJSON('rest/api/servicos/lng/'+longitude+'/lat/'+latitude+'/categorias/INSS,RFB,ASS_SOCIAL,UBS,ENS_BASICO,CARTORIO', function(pontos) {
			$.each(pontos, function(index, ponto) {
					var marker = new google.maps.Marker({
						position: new google.maps.LatLng(ponto.localizacao.coordenadas.latitude, ponto.localizacao.coordenadas.longitude),
						title: ponto.categoria,
						map: map,
						icon: 'img/' + ponto.categoria + '.png'
					});

					var infowindow = new google.maps.InfoWindow(), marker;
					google.maps.event.addListener(marker, 'click', (function(marker, i) {
						return function() {
							var msgbalao = "";
							if (ponto.nome) {
								msgbalao += ponto.nome;
							}
							if (ponto.atendimento) {
								msgbalao += "<br>ATENDIMENTO: " + ponto.atendimento;
							}
							if (ponto.contato) {
								if (ponto.contato.telefones) {
									msgbalao += "<br>TELEFONE: " + ponto.contato.telefones;
								}
								if (ponto.contato.email) {
									msgbalao += "<br>EMAIL: " + ponto.contato.email;
								}
							}
							infowindow.setContent(msgbalao);
							infowindow.open(map, marker);
						}
					})(marker))
			});
		});
	}
	
	function carregarNoMapa(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					latitude = results[0].geometry.location.lat();
					longitude = results[0].geometry.location.lng();
					$('#txtEndereco').val(results[0].formatted_address);
					var location = new google.maps.LatLng(latitude, longitude);
					marker.setPosition(location);
					map.setCenter(location);
					map.setZoom(16);
				}
			}
		});
	}

	$("#btnEndereco").click(function() {
		if($("#txtEndereco").val() == "") {
			$("#txtEndereco").val("Sorocaba, São Paulo, Brasil");
		}
		carregarNoMapa($("#txtEndereco").val());
	})
	
	$("#txtEndereco").blur(function() {
		if($(this).val() == "") {
			$("#txtEndereco").val("Sorocaba, São Paulo, Brasil");
		}
		carregarNoMapa($(this).val());
	})
	
	$("#txtEndereco").autocomplete({
		source: function (request, response) {
			geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
				response($.map(results, function (item) {
					return {
						label: item.formatted_address,
						value: item.formatted_address,
						latitude: item.geometry.location.lat(),
          				longitude: item.geometry.location.lng()
					}
				}));
			})
		},
		select: function (event, ui) {
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			marker.setPosition(location);
			map.setCenter(location);
			map.setZoom(16);
		}
	});
	
});

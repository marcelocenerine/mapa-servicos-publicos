var map;
var geocoder;
var markers = [];
var infowindow = new google.maps.InfoWindow();
var defaultZoom = 4;
var placeZoom = 15;
var defaultLat = -14.235004;
var defaultlng = -51.92528;
	
	
function showWhereIam() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(
			function (position) {
				defineSearchPlace(position.coords.latitude, position.coords.longitude);
				loadPoints(position.coords.latitude, position.coords.longitude);
			}
		);
	}
}

function defineSearchPlace(lat, lng) {
	var location = new google.maps.LatLng(lat, lng);
	var marker = new google.maps.Marker({
		map: map,
		draggable: false,
	});
	marker.setPosition(location);
	map.setCenter(location);
	map.setZoom(placeZoom);
}

function loadPoints(latitude, longitude) {
	clearMarkers();
	hideStreetView();
	// APAGAR LINHA ABAIXO QUANDO A CONSULTA POR CATEGORIAS ESTIVER PRONTA
	var servicos = 'CORREIOS, CARTORIO, ENS_BASICO, ENS_SUPERIOR, RFB, ASS_SOCIAL, INSS, COM_TERAP, SINE, UBS';
	
	$.getJSON('rest/api/servicos/lng/' + longitude + '/lat/' + latitude + '/categorias/' + servicos, function(pontos) {
		$.each(pontos, function(index, ponto) {
			addPoint(ponto);
		});
	});	
}

function addPoint(ponto) {
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(ponto.localizacao.coordenadas.latitude, ponto.localizacao.coordenadas.longitude),
		title: ponto.categoria.descricao,
		map: map,
		icon: 'img/' + ponto.categoria.tipo + '.png'
	});
	trackMarker(marker);

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
					msgbalao += "<p>Telefones: " + ponto.contato.telefones.join(', ') + "</p>";
				}
				if (ponto.contato.email) {
					msgbalao += "<p>Email: " + ponto.contato.email; + "</p>";
				}
			}
			infowindow.setContent(msgbalao);
			infowindow.open(map, marker);
		};
	})(marker));
}

function trackMarker(marker) {
	markers.push(marker);
}

function clearMarkers() {
	$(markers).each(function(i, e) { 
		e.setMap(null);
		}
	);
	markers = [];
}

function hideStreetView() {
	map.getStreetView().setVisible(false);
}

function bindComponentEvents() {
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
			defineSearchPlace(ui.item.latitude, ui.item.longitude);
			loadPoints(ui.item.latitude, ui.item.longitude);
		}
	});	
}

$(document).ready(function () {
	var options = {
		zoom: defaultZoom,
		center: new google.maps.LatLng(defaultLat, defaultlng),
		panControl: true,
		streetViewControl: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('mapa'), options);
	geocoder = new google.maps.Geocoder();
	
	bindComponentEvents();
	showWhereIam();
});

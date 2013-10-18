var map;
var geocoder;
var markers = [];
var infowindow = new google.maps.InfoWindow();
var defaultZoom = 4;
var placeZoom = 15;
var defaultLat = -14.235004;
var defaultlng = -51.92528;
var currentLocation;
var currentLocationMarker;
	
	
function showWhereIam() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(
			function (position) {
				defineCurrentLocation(position.coords.latitude, position.coords.longitude);
				translateLocationToAddress(position.coords.latitude, position.coords.longitude, $('#txtEndereco'));
				loadPoints();
			}
		);
	}
}

function defineCurrentLocation(lat, lng) {
	currentLocation = new google.maps.LatLng(lat, lng);
	currentLocationMarker.setPosition(currentLocation);
	map.setCenter(currentLocation);
	map.setZoom(placeZoom);
}

function translateLocationToAddress(lat, lng, element) {
	var location = new google.maps.LatLng(lat, lng);
	geocoder.geocode({'latLng' : location}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[3]) {
				$(element).val(results[3].formatted_address);
			}
		}
	});
}

function searchAddress() {
	 geocoder.geocode({ 'address': $('#txtEndereco').val() + ', Brasil', 'region': 'BR' }, function (results, status) {
		 if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					latitude = results[0].geometry.location.lat();
					longitude = results[0].geometry.location.lng();
					defineCurrentLocation(latitude, longitude);
					loadPoints();
				}
			}
		});
 }

function loadPoints() {
	hideStreetView();
	var servicos = getCheckBox();
	if (servicos) {
		blockMap();
		$.getJSON('rest/api/servicos/lng/' + currentLocation.lng() + '/lat/' + currentLocation.lat() + '/categorias/' + servicos, function(pontos) {
			clearMarkers();
			$.each(pontos, function(index, ponto) {
				addPoint(ponto);
			});
			unblockMap();
		});	
	} else {
		clearMarkers();
	}
}

function blockMap() {
	$('#mapa').block({ css: { 
		border: 'none', 
		padding: '15px', 
		backgroundColor: '#000', 
		'-webkit-border-radius': '10px', 
		'-moz-border-radius': '10px', 
		opacity: .5, 
		color: '#fff' 
	} , 
	message : 'Aguarde...'});
}

function unblockMap() {
	$('#mapa').unblock();
}

function getCheckBox() {
	var selecao = '';
	$("input:checked[type=checkbox]").each(function() {
		selecao += $(this).val() + ',';
	});
	return selecao;
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
			infowindow.setContent(formatInfoWindowText(ponto));
			infowindow.open(map, marker);
		};
	})(marker));
}

function formatInfoWindowText(ponto) {
	var text = '<p><strong>' + ponto.categoria.descricao + '</strong></p>';
	if (ponto.nome) {
		text += '<p>' + ponto.nome + '</p>';
	}
	if (ponto.atendimento) {
		text += '<p>Atendimento: ' + ponto.atendimento + '</p>';
	}
	if (ponto.contato) {
		if (ponto.contato.telefones) {
			text += '<p>Telefones: ' + ponto.contato.telefones.join(', ') + '</p>';
		}
		if (ponto.contato.email) {
			text += '<p>Email: ' + ponto.contato.email; + '</p>';
		}
	}
	return text;
}

function trackMarker(marker) {
	markers.push(marker);
}

function clearMarkers() {
	$(markers).each(function(i, e) { 
		e.setMap(null);
	});
	markers = [];
}

function hideStreetView() {
	map.getStreetView().setVisible(false);
}

function bindComponentEvents() {
	$('#txtEndereco').autocomplete({
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
			defineCurrentLocation(ui.item.latitude, ui.item.longitude);
			loadPoints();
		}
	});	
	
	$('#txtEndereco').click(function() {
		  $(this).val('');
	});
	
	$('#txtEndereco').keypress(function(e){
		var tecla = (e.keyCode ? e.keyCode : e.which);
		 if(tecla == 13 &&  $('#txtEndereco').val()){
			 searchAddress();
		 }
	});
	
	$('#btnEndereco').click(function() {
		if ( $('#txtEndereco').val()) {
			searchAddress();
		}
	});
	
	$('#selecionaTudo').click(function() {
		$('input[type=checkbox]').trigger('click');
		if ($('input[type=checkbox]').is(':checked')) {
			$('#selecionaTudo').html('Desmarcar todos serviços');
		} else {
			$('#selecionaTudo').html('Marcar todos serviços');
		} 
	});
}

function initialize() {
	$('#txtEndereco').val('');
	//$("input:checkbox").attr("checked",true);
}

$(document).ready(function () {
	currentLocation = new google.maps.LatLng(defaultLat, defaultlng);
	var options = {
		zoom: defaultZoom,
		center: currentLocation,
		panControl: true,
		streetViewControl: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('mapa'), options);
	currentLocationMarker = new google.maps.Marker({
		map: map,
		draggable: false,
		animation: google.maps.Animation.DROP
	});
	geocoder = new google.maps.Geocoder();
	
	initialize();
	bindComponentEvents();
	showWhereIam();	
});

var map;
var geocoder;
var markers = [];
var infowindow = new google.maps.InfoWindow();
var directionsDisplay;
var directionsService;
var defaultZoom = 4;
var placeZoom = 16;
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
                                ga('send', 'event', 'Geolocalizao Utilizada', 'browser');
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
        var endereco = $.trim($('#txtEndereco').val());
         geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
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
        clearRoute();
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
        $('input:checked[type=checkbox]').each(function() {
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

function closeInfoWindow() {
        infowindow.close();
}

function modeRoute(mode) {
	switch (mode){
		case 1: return google.maps.TravelMode.WALKING;
		//case 2: return google.maps.TravelMode.BICYCLING;
		default: return google.maps.TravelMode.DRIVING;
	}
}

function drawRoute(lat, lng, mode) {
        clearRoute();
        var request = {
                origin: currentLocation,
                destination: new google.maps.LatLng(lat, lng),
                travelMode: modeRoute(mode)
        };
        var options = {
        		draggable: true,
        };
        directionsDisplay = new google.maps.DirectionsRenderer(options);
        directionsDisplay.setMap(map);
        directionsService.route(request, function(response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(response);
                }
        });
        $('#close').css('display', 'block');
        $('#rotamsg').css('display', 'block');
        directionsDisplay.setPanel($('#rota')[0]);
}

function clearRoute() {
        if(directionsDisplay) {
         directionsDisplay.setMap(null);
         directionsDisplay.setPanel(null);
         directionsDisplay = null;
        }
}

function formatInfoWindowText(ponto) {
		var text = '<div><strong>' + ponto.categoria.descricao + '</strong></div>';
	    text += '<div><a title="Clique e visualize o local através do Google Street View" href="#" onclick="javascript:showStreetView('+ ponto.localizacao.coordenadas.latitude + ',' + ponto.localizacao.coordenadas.longitude + ')">';
	    text += '<img src="http://maps.googleapis.com/maps/api/streetview?size=300x200&fov=110&pitch=10&location='+ ponto.localizacao.coordenadas.latitude + ',%20' + ponto.localizacao.coordenadas.longitude + '&sensor=false" />';
	    text += '</a></div>';
        if (ponto.nome) {
                text += '<div><strong>' + ponto.nome + '</strong></div>';
        }
        if (ponto.localizacao) {
        	if (ponto.localizacao.endereco) {
        		text += '<div>' + ponto.localizacao.endereco + '</div>';
        	}
        	if (ponto.localizacao.bairro && ponto.localizacao.cidade) {
        		text += '<div>' + ponto.localizacao.bairro + ' - ' + ponto.localizacao.cidade;
        	}
        }
        if (ponto.atendimento) {
                text += '<div>Atendimento: ' + ponto.atendimento + '</div>';
        }
        if (ponto.contato) {
                if (ponto.contato.telefones) {
                        text += '<div>Telefones: ' + ponto.contato.telefones.join(', ') + '</div>';
                }
                if (ponto.contato.email) {
                        text += '<div>Email: ' + ponto.contato.email; + '</div>';
                }
        }
        text += '<div><a title="Clique e confira trajeto caminhando" href="#" onclick="javascript:closeInfoWindow();drawRoute('+ ponto.localizacao.coordenadas.latitude + ',' + ponto.localizacao.coordenadas.longitude + ', 1);"><img src="img/walking.jpg" alt="Como chegar a pé?" title="Como chegar a pé?" height="28" width="17" />Como chegar a pé?</a></div>';
        text += '<div><a title="Clique e confira trajeto dirigindo" href="#" onclick="javascript:closeInfoWindow();drawRoute('+ ponto.localizacao.coordenadas.latitude + ',' + ponto.localizacao.coordenadas.longitude + ', 0);"><img src="img/driving.jpg" alt="Como chegar de carro?" title="Como chegar de carro?" height="24" width="24" />Como chegar de carro?</a></div>';
        ga('send', 'event', 'Balao Informativo ' + ponto.categoria.descricao + ' em ' + ponto.localizacao.cidade + '/' + ponto.localizacao.uf, 'click');
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

function showStreetView(lat, lng) {
	var panorama = map.getStreetView();
	var options = {
		    position: new google.maps.LatLng(lat, lng),
		    visible: true, 
		    zoomControl: false,
		    panControl: false
	};
	panorama.setOptions(options);
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
                        ga('send', 'event', 'Clique Autocomplete em ' + ui.item.value, 'click');
                        defineCurrentLocation(ui.item.latitude, ui.item.longitude);
                        loadPoints();
                }
        });        
        
        $('#txtEndereco').click(function() {
                 $(this).val('');
        });
        
        $('#txtEndereco').keypress(function(e){
                var tecla = (e.keyCode ? e.keyCode : e.which);
                var endereco = $.trim($('#txtEndereco').val());
                if(tecla == 13 && endereco){
                         searchAddress();
                 }
        });
        
        $('#btnEndereco').click(function() {
                var endereco = $.trim($('#txtEndereco').val());
                if (endereco) {
                        searchAddress();
                }
        });
        
        $('input:checkbox').click(function() {
                loadPoints();
        });
        
        $('#sobrelink').click(function() {
        		autoOpen: false, 
        		$("#sobre").dialog({ modal: true, width: 630 });
        		ga('send', 'event', 'Sobre', 'menu');
        });
        
        $('#comofuncionalink').click(function() {
        	autoOpen: false, 
    		$("#comofunciona").dialog({ modal: true, width: 630 });
        	ga('send', 'event', 'Como Funciona', 'menu');
        });
        
        $('#dadoslink').click(function() {
        		autoOpen: false, 
        		$("#dados").dialog({ modal: true, width: 630 });
        		ga('send', 'event', 'Dados', 'menu');
        });
        
        $('#contatolink').click(function() {
        		autoOpen: false, 
        		$("#contato").dialog({ modal: true, width: 630 });
        		ga('send', 'event', 'Contato', 'menu');
        });
        
        $('#close').click(function() {
            $('#close').css('display', 'none');
            $('#rotamsg').css('display', 'none');
        	clearRoute();
        });
}

function loadFacebookAPI() {
	$.getScript('//connect.facebook.net/pt_BR/all.js', function(){
		FB.init({
			appId      : $('#fb-app-id').val(),
			status     : false,
			cookie     : true,
			xfbml      : false
		});
		
		$(".boxFacebook a.fb-login-button").on('click', function() {
			if (!$(this).hasClass('disableFacebook')) {
				FB.login(function(response) {									
		      	    if (response.authResponse) {
		      	    	FB.api(response.authResponse.userID + '?fields=id,name,picture.width(30).height(30)', function(resp){
		      	    		$('.boxFacebook input#name').val(resp.name);
		      	    		$('.boxFacebook input#pictureUrl').val(resp.picture.data.url);
		    				$('.boxFacebook form').attr('action', 'user/id/' + resp.id + '/login').submit();
		    			});
		      	    }
				});
			}
		});
		
		$("a.fb-login-button").removeClass('disableFacebook');
	});
}

function loadGMaps() {
	currentLocation = new google.maps.LatLng(defaultLat, defaultlng);
	var options = {
		zoom: defaultZoom,
		center: currentLocation,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		
		mapTypeControl: true,
	    mapTypeControlOptions: {
	        position: google.maps.ControlPosition.TOP_RIGHT
	    },
	    panControl: true,
	    panControlOptions: {
	        position: google.maps.ControlPosition.RIGHT_TOP
	    },
	    zoomControl: true,
	    zoomControlOptions: {
	        style: google.maps.ZoomControlStyle.LARGE,
	        position: google.maps.ControlPosition.RIGHT_TOP
	    },
	    scaleControl: true,
	    streetViewControl: true,
	    streetViewControlOptions: {
	        position: google.maps.ControlPosition.RIGHT_TOP
	    }
	};
	map = new google.maps.Map(document.getElementById('mapa'), options);
	currentLocationMarker = new google.maps.Marker({
		map: map,
		draggable: false,
		animation: google.maps.Animation.DROP
	});
	geocoder = new google.maps.Geocoder();
	directionsService = new google.maps.DirectionsService();
}

function initialize() {
        $('#txtEndereco').val('');
        loadGMaps();
        loadFacebookAPI();
}

$(document).ready(function () {
	initialize();
	bindComponentEvents();
	showWhereIam();	
});
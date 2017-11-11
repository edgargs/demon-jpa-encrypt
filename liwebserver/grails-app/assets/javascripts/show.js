var latIni;
var lngIni;

var route_map_view;

var myZoom = 13;

function showRouteView() {
	//console.log('Muestra puntos de control de la ruta seleccionada');
	var myLatlng = new google.maps.LatLng(latIni,lngIni);
	
	route_map_view = new google.maps.Map($('#route_map_view')[0],{
		center: myLatlng,
		zoom: myZoom,
		disableDefaultUI: true
	});
}

function showMarker() {
	var myLatlng = new google.maps.LatLng({lat:latIni, lng:lngIni});
			
	var event_al = new google.maps.Marker({
		map: route_map_view,
		position: myLatlng
		//icon: lineSymbol
	});
}


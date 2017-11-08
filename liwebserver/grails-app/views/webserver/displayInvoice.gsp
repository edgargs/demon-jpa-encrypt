<!DOCTYPE html>
<html>
	<head>
		<script
		  src="https://code.jquery.com/jquery-3.2.1.min.js"
		  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		  crossorigin="anonymous"></script>
		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCHtrkNiJA_cCVVYwhRIgZRO26QqrvGaY"
			type="text/javascript"></script>
	</head>
	<body>
		<div id="route_map_view" style="height:800px"></div>
		
		<script>
			window.onload = function(e){
				showRouteView();
			}
			
var latIni = -12;
var lngIni = -77;

var myframeview = '#frmRouteMap';

var routePath;
var routePathReturn;
var route_map_view;

var vehicleID_ant = 0;
var vehicleID = 0;

var arrLabelCP = [];
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
		</script>
	</body
</html>
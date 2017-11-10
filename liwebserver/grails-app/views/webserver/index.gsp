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
		
		<asset:javascript src="show.js" />
	</head>
	<body>
		
		<div id="route_map_view" style="height:800px"></div>
		
		<script>
			var latIni = ${latitude};
			var lngIni = ${longitude};
			console.log(latIni)
			console.log(lngIni)
			window.onload = function(e){
				showRouteView();
				showMarker();
			}

		</script>
	</body
</html>
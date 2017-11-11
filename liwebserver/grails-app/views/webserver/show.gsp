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
			console.log("Carga de pagina")
			var liid = '${liid}';

			var timerId;
			
			window.onload = function(e){
				if(liid) {
					iniciaConsulta(liid)
				} else {
					console.log("No asignado")
				}
			}

			function iniciaConsulta(liid) {
				//https://javascript.info/settimeout-setinterval
				timerId = setInterval(() => consultaUbicacion(liid), 3*1000);
			}

			function consultaUbicacion(liid) {
				console.log("Consulta ubicacion")
				var URL="${createLink(controller:'reception',action:'check')}";

				$.ajax({
					url:URL,
					data: {id:liid},
					success: function(resp){
						console.log(resp);
						latIni = eval(resp.latitude);
						lngIni = eval(resp.longitude);
						clearInterval(timerId)
						console.log("Detencion consulta")
						showRouteView();
						showMarker();
					}
				});
			}

		</script>
	</body
</html>
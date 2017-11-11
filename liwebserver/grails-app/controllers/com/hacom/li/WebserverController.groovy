package com.hacom.li

import com.hacom.li.webserver.WSWarrantService
import grails.web.RequestParameter
import org.springframework.beans.factory.annotation.Autowired

class WebserverController {

	@Autowired
	WSWarrantService warrantService

    def index() { }
	
	def show(String latitude, String longitude) {
		println "latitude=$latitude&longitude=$longitude"
		render view:'show', model: [latitude: latitude, longitude: longitude]
	}

	def echo(@RequestParameter('text') String p_liid) {
		render warrantService.echo(p_liid)
	}

	def warrant() {
		String p_liid = params.id
		if (p_liid == null) {
			render "Ingrese la direccion correcta."
		} else {
			String resp = "ACK" //warrantService.find(p_liid)
			switch (resp) {
				case "ACK":
					render view: 'show', model: [liid: p_liid]
					break;
				case "ERR_CONN":
					render "Error al conectar con el servicio. Pruebe en unos minutos."
					break;
				default:
					render resp
			}
		}
	}

}

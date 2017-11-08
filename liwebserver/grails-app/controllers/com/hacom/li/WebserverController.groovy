package com.hacom.li

import grails.web.RequestParameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

class WebserverController {

	@Autowired
	WSWarrantService warrantService

    def index() { }
	
	def show(String latitude, String longitude) {
		println "latitude=$latitude&longitude=$longitude"
		render view:'index', model: [latitude: latitude, longitude: longitude]
	}

	def echo(@RequestParameter('text') String p_liid) {
		render warrantService.echo(p_liid)
	}

	def warrant(@RequestParameter('liid') String p_liid) {
		render warrantService.find(p_liid)
	}

}

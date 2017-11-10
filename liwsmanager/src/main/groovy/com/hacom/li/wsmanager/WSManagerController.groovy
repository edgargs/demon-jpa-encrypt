package com.hacom.li.wsmanager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

import com.hacom.li.util.LIEncryption
import com.hacom.li.util.SenderThread

import groovy.util.logging.Slf4j

/**
 * https://www.infoq.com/articles/boot-microservices
 */
@RestController
@RequestMapping("/wsmanager")
@Slf4j
class WSManagerController {

	WarrantDao warrantDao = new WarrantDao()

	@RequestMapping("/hello")
	String hello(@RequestParam(value="name", defaultValue = "World") String name) {
		return "Hello $name!"
	}

	@RequestMapping("/echo/{text}")
	String echo(@PathVariable String text) {
		return text
	}

	@RequestMapping("/find/{liid}")
	String findWarrant(@PathVariable String liid) {

		String resp = "ACK"
		// 1. Busca liid
		def warrant = warrantDao.searchById(liid)

		if (warrant == null) {
			resp = "Not found"
		} else {
			def p_msisdn = LIEncryption.decrypt(warrant?.msisdn)
			// 2. Validaciones
			if (warrant.status == 3) {
				resp = "Outdate"
			} else {
				println "inicio"
				Thread.start{
					warrant.id = warrant?.warrantid
					warrant.liid = liid
					warrant.msisdn = p_msisdn
					warrant.lemf_ip = LIEncryption.decrypt(warrant?.lemf_ip)
					warrant.lemf_port = LIEncryption.decrypt(warrant?.lemf_port)
					warrant.ftp_user = LIEncryption.decrypt(warrant?.ftp_user)
					warrant.ftp_pass = LIEncryption.decrypt(warrant?.ftp_pass)
					println warrant
					SenderThread.sendPOSTJSON(warrant)

					SenderThread.sendPOSTXML(liid,p_msisdn)
				}
				println "fin"
			}
		}

		return resp
	}
}
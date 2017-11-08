package com.hacom.li.wsmanager

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

/**
 * https://www.infoq.com/articles/boot-microservices
 */
@RestController
@RequestMapping("/wsmanager")
class WSManagerController {

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
		println liid
		return "OK"
	}
}
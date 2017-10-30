package com.hacom.limanager

import com.hacom.li.util.LIEncryption
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@Slf4j
class DemoApplication{

	static void main(String[] args) {
		SpringApplication.run DemoApplication, args

		//MultiServer.main()
	}

	@Bean
	public CommandLineRunner demo(WarrantRepository repository) {
		LIEncryption.keyEncrypt = ConfigManager.keyEncrypt
		sleep(3000)
		while(true) {
			log.warn "key: $LIEncryption.keyEncrypt"
			log.debug LIEncryption.encrypt("Edgar")
			def countWarrants = repository.findOne(1L)
			log.info countWarrants?.msisdn
			sleep(3000)
		}
	}
}
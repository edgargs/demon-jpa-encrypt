package com.hacom.li.manager

import com.hacom.li.util.LIEncryption
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@Slf4j
class ManagerApplication {

	static void main(String[] args) {
		SpringApplication.run ManagerApplication, args

		//MultiServer.main()
	}

	@Bean
	public CommandLineRunner manager(WarrantRepository repository) {
		LIEncryption.keyEncrypt = ConfigManager.keyEncrypt
		sleep(3000)
		while(true) {

			def countWarrants = repository.findByStatus(1)
			countWarrants?.each { it ->

				Date now = new Date()

				if (now.after(it.begin_datetime) && now.before(it.end_datetime) ) {
					log.debug "now: $now"
					log.debug "$it"
				} else if (now.after(it.end_datetime)) {
					log.info "Outdate $it.liid"
					it.status = 3
					it.lastUpdated = new Date()
					//repository.save(it)
					repository.setComplete(it.status,it.lastUpdated,it.id)
				}
			}
			sleep(3000)
		}
	}
}
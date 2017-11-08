package com.hacom.li.wsmanager

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import com.hacom.li.util.LIEncryption

@SpringBootApplication
class WSManagerApplication {

	static void main(String[] args) {
		SpringApplication.run WSManagerApplication, args
	}

	@Bean
	public CommandLineRunner load() {
		LIEncryption.keyEncrypt = ConfigManager.keyEncrypt
		return null
	}
}

package com.hacom.limanager

import com.hacom.li.util.LIEncryption
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
class MultiServer {

	static def url = 'jdbc:mysql://127.0.0.1:3306/lidb'
	static def user = 'lidbus'
	static def password = 'testing'
	static def driver = 'com.mysql.jdbc.Driver'
	static def sql = Sql.newInstance(url, user, password, driver)

	static void main(String[] args) {

		LIEncryption.keyEncrypt = ConfigManager.keyEncrypt
		sleep(3000)
		while(true) {
			log.warn "key: $LIEncryption.keyEncrypt"
			log.debug LIEncryption.encrypt("Edgar")
			def countWarrants = sql.firstRow("SELECT * FROM warrant")
			log.info LIEncryption.decrypt(countWarrants?.msisdn)
			sleep(3000)
		}

		sql.close()
	}
}
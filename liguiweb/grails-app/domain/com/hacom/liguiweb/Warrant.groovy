package com.hacom.liguiweb

import com.hacom.liguiweb.util.LIEncryption
import groovy.util.logging.Slf4j

@Slf4j
class Warrant {

	int id
	String liid
	int targetid_type
	String msisdn
	String imei

    static constraints = {
		liid maxSize: 45
		targetid_type inList: [1,2]
		msisdn nullable: true, maxSize: 20
		imei nullable: true, maxSize: 20
    }

	static mapping = {
		id generator: 'identity', column: "warrantid"
	}

	def beforeInsert() {
		log.debug "before insert warrant"
		msisdn = LIEncryption.encrypt(msisdn)
	}

	def beforeUpdate() {
		msisdn = LIEncryption.encrypt(msisdn)
	}

	def afterLoad() {
		msisdn = LIEncryption.decrypt(msisdn)
	}
}

package com.hacom.liguiweb

import groovy.util.logging.Slf4j
import groovy.transform.ToString

@Slf4j
@ToString
class Warrant {

    //static mapWith = "none"
    int id
    
	String liid
	int targetid_type = 1
	String msisdn
	String imei
	Date warrant_date
	String reference_name
	String lemf_ip
	int lemf_port
	int period = 1
	int timeslot_enable
	int ia_enable
	String filepath = "."
	int li_type
	Date begin_datetime
	Date end_datetime
	String observations
    
    static constraints = {
		liid maxSize: 5
		targetid_type inList: [1,2]
		//msisdn nullable: true, maxSize: 20
		imei nullable: true, maxSize: 20
		li_type inList: [1,2]
		filepath nullable: true
		period min: 1
    }

	static mapping = {
        table "warrant_tmp"
        version false
    }

}

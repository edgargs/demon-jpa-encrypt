package com.hacom.liguiweb

import com.hacom.li.util.LIEncryption
import groovy.util.logging.Slf4j
import groovy.transform.ToString

@Slf4j
@ToString
class WarrantEncrypt {

    int id

	String liid
	String targetid_type
	String msisdn
	String imei
	Date warrant_date
	String reference_name
	String lemf_ip
	String lemf_port
	String ftp_user
	String ftp_pass
	String period
	String timeslot_enable
	String ia_enable
	String filepath
	String li_type
	Date begin_datetime
	Date end_datetime
	String observations

	Date dateCreated
	Date lastUpdated
	int status = 1
    
    static constraints = {
		imei nullable: true
    }
    
    static mapping = {
        table "warrant"
            
		id generator: 'identity', column: "warrantid"

		dateCreated column: "ts_created"
		lastUpdated column: "ts_modified"
        
        liid sqlType: 'varchar(255)'        
        msisdn sqlType: 'varchar(255)'
        imei sqlType: 'varchar(255)'
        
        warrant_date sqlType: 'varchar(255)'
        begin_datetime sqlType: 'varchar(255)'
        end_datetime sqlType: 'varchar(255)'
	}
    
    def beforeInsert() throws Exception {
        log.info "Start beforeInsert"
		liid = LIEncryption.encrypt(liid)
        targetid_type = LIEncryption.encrypt(targetid_type)
		msisdn = LIEncryption.encrypt(msisdn)
        imei = imei?LIEncryption.encrypt(imei):imei
	    //warrant_date = LIEncryption.encrypt(warrant_date)
	    reference_name = LIEncryption.encrypt(reference_name)
	    lemf_ip = LIEncryption.encrypt(lemf_ip)
        lemf_port = LIEncryption.encrypt(lemf_port)
		ftp_user = LIEncryption.encrypt(ftp_user)
		ftp_pass = LIEncryption.encrypt(ftp_pass)
	    period = LIEncryption.encrypt(period)
	    timeslot_enable = LIEncryption.encrypt(timeslot_enable)
	    ia_enable = LIEncryption.encrypt(ia_enable)
	    filepath = LIEncryption.encrypt(filepath)
	    li_type = LIEncryption.encrypt(li_type)
	    //begin_datetime = LIEncryption.encrypt(begin_datetime)
	    //end_datetime = LIEncryption.encrypt(end_datetime)
        observations = LIEncryption.encrypt(observations)
        log.info "End beforeInsert"
        log.debug "warrant encrypt: $this"
    }

	def beforeUpdate() {
		liid = LIEncryption.encrypt(liid)
        targetid_type = LIEncryption.encrypt(targetid_type)
		msisdn = LIEncryption.encrypt(msisdn)
		imei = imei?LIEncryption.encrypt(imei):imei
	    //warrant_date = LIEncryption.encrypt(warrant_date)
	    reference_name = LIEncryption.encrypt(reference_name)
	    lemf_ip = LIEncryption.encrypt(lemf_ip)
        lemf_port = LIEncryption.encrypt(lemf_port)
		ftp_user = LIEncryption.encrypt(ftp_user)
		ftp_pass = LIEncryption.encrypt(ftp_pass)
	    period = LIEncryption.encrypt(period)
	    timeslot_enable = LIEncryption.encrypt(timeslot_enable)
	    ia_enable = LIEncryption.encrypt(ia_enable)
	    filepath = LIEncryption.encrypt(filepath)
	    li_type = LIEncryption.encrypt(li_type)
	    //begin_datetime = LIEncryption.encrypt(begin_datetime)
	    //end_datetime = LIEncryption.encrypt(end_datetime)
        observations = LIEncryption.encrypt(observations)
	}

	def afterLoad() {
		liid = LIEncryption.decrypt(liid)
        targetid_type = LIEncryption.decrypt(targetid_type)
		msisdn = LIEncryption.decrypt(msisdn)
        imei = imei?LIEncryption.decrypt(imei):imei
	    //warrant_date = LIEncryption.decrypt(warrant_date)
	    reference_name = LIEncryption.decrypt(reference_name)
	    lemf_ip = LIEncryption.decrypt(lemf_ip)
        lemf_port = LIEncryption.decrypt(lemf_port)
		ftp_user = ftp_user?LIEncryption.decrypt(ftp_user):ftp_user
		ftp_pass = ftp_pass?LIEncryption.decrypt(ftp_pass):ftp_pass
	    period = LIEncryption.decrypt(period)
	    timeslot_enable = LIEncryption.decrypt(timeslot_enable)
	    ia_enable = LIEncryption.decrypt(ia_enable)
	    filepath = LIEncryption.decrypt(filepath)
	    li_type = LIEncryption.decrypt(li_type)
	    //begin_datetime = LIEncryption.decrypt(begin_datetime)
	    //end_datetime = LIEncryption.decrypt(end_datetime)
        observations = LIEncryption.decrypt(observations)
	}

	/*def beforeValidate() {
		log.debug "warrant_date: $warrant_date"
		log.debug "begin_datetime: $begin_datetime"
	}*/
}

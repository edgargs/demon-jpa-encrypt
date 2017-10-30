package com.hacom.limanager

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id

/**
 * Created by Edgar Rios on 30/10/2017.
 * https://spring.io/guides/gs/accessing-data-mysql/
 */
@Entity
@EntityListeners(WarrantListener.class)
class Warrant {

    @Id
    @Column(name="warrantid")
    long id

    String liid
    String targetid_type
    String msisdn
    String imei
    Date warrant_date
    String reference_name
    String lemf_ip
    String lemf_port
    String period
    String timeslot_enable
    String ia_enable
    String filepath
    String li_type
    Date begin_datetime
    Date end_datetime
    String observations

    @Column(name="ts_created")
    Date dateCreated
    @Column(name="ts_modified")
    Date lastUpdated
    int status

}

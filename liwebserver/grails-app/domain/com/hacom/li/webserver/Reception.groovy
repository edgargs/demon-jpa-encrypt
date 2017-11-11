package com.hacom.li.webserver

import com.hacom.li.util.LIEncryption
import groovy.util.logging.Slf4j
import groovy.transform.ToString

/**
 * Created by Edgar Rios on 10/11/2017.
 */
@Slf4j
@ToString
class Reception {
    int id

    String liid
    String latitude
    String longitude

    Date dateCreated
    Date lastUpdated

    static mapping = {
        id generator: 'identity', column: "receptiontid"

        dateCreated column: "ts_created"
        lastUpdated column: "ts_modified"

        liid sqlType: 'varchar(255)'
        latitude sqlType: 'varchar(255)'
        longitude sqlType: 'varchar(255)'
    }

    def beforeInsert() throws Exception {
        log.info "Start beforeInsert"
        liid = LIEncryption.encrypt(liid)

        latitude = LIEncryption.encrypt(latitude)
        longitude = longitude?LIEncryption.encrypt(longitude):longitude

    }

    def beforeUpdate() {
        liid = LIEncryption.encrypt(liid)

        latitude = LIEncryption.encrypt(latitude)
        longitude = longitude?LIEncryption.encrypt(longitude):longitude
    }

    def afterLoad() {
        liid = LIEncryption.decrypt(liid)

        latitude = LIEncryption.decrypt(latitude)
        longitude = longitude?LIEncryption.decrypt(longitude):longitude
    }
}

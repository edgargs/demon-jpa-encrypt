package com.hacom.li

import com.hacom.li.webserver.Reception
import com.hacom.li.webserver.ReceptionService
import com.hacom.li.util.LIEncryption
import grails.converters.JSON

/**
 * Created by Edgar Rios on 09/11/2017.
 */
class ReceptionController {

    ReceptionService receptionService

    def save(String latitude, String longitude) {
        String liid = params.id
        println "latitude=$latitude&longitude=$longitude"

        String enc_liid = LIEncryption.encrypt(liid)
        def r = Reception.findByLiid(enc_liid)
        if ( !r ) {
            r = new Reception(liid: liid, latitude: latitude, longitude: longitude)
        } else {
            r.setLatitude(latitude)
            r.setLongitude(longitude)
        }
        println r
        receptionService.save(r)

        render "ACK"
    }

    //https://stackoverflow.com/questions/34569655/ajax-function-and-grails-controller
    def check() {
        String liid = params.id

        String enc_liid = LIEncryption.encrypt(liid)
        def r = Reception.findByLiid(enc_liid)
        render r as JSON
    }
}

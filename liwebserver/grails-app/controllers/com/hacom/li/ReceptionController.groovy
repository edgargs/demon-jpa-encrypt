package com.hacom.li

/**
 * Created by Edgar Rios on 09/11/2017.
 */
class ReceptionController {

    def save(String latitude, String longitude) {
        println params.id
        println "latitude=$latitude&longitude=$longitude"
        render "ACK"
    }
}

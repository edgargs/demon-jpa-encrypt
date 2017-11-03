package com.hacom.li.manager

import groovy.util.logging.Slf4j
import groovyx.net.http.*


/**
 * Created by Edgar Rios on 02/11/2017.
 */
@Slf4j
class SenderThread extends Thread{

    int liid
    int msisdn
    int period
    boolean detener

    void run() {
        log.debug "ServerUDPThread=$liid:$period"
        detener = false;
        while(!detener) {
            sendPOSTXML(liid,msisdn)
            sleep(period*60*1000)
        }
    }
    //https://stackoverflow.com/questions/28356283/groovy-httpbuilder-post-xml-with-basic-authentication
    def sendPOSTXML(def p_liid, def p_msisdn) {
        if(true) {
            log.debug "Respuesta envio $p_liid-$p_msisdn: OK"
            return;
        }
        def p_xml = '''
            <svc_init>
            <hdr>
                <client>
                    <id>GPPIPcom</id>
                    <pwd>testpiloto</pwd>
                 </client>
                 <liid>$p_liid</liid>
             </hdr>
             <slir res_type=\"SYNC\">
                 <msids>
                     <msid>
                         $p_msisdn
                     </msid>
                 </msids>
                 <loc_type type=\"CURRENT\"/>
              </slir>
        </svc_init>
            '''

        def http = new HTTPBuilder('http://192.168.5.39/LIGMLC/lilcs')
        http.contentType = ContentType.TEXT
        http.headers = [Accept: 'application/xml', charset: 'UTF-8']

        http.request(Method.POST) {
            body = p_xml

            response.success = { resp, reader ->
                //log.debug "Content-Type: ${resp.headers.'Content-Type'}"
                log.debug "Respuesta envio $liid: "+reader.text
                //log.debug "$resp.statusLine   Respond rec"
            }

            /*
            response.failure = { resp, xml ->
                println resp
                //println "Content-Type: ${resp.headers.'Content-Type'}"
                println 'error\n'+xml
            }*/
        }

    }
}

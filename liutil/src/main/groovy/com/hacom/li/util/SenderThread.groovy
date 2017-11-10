package com.hacom.li.util

import groovy.util.logging.Slf4j
import groovyx.net.http.*

/**
 * Created by Edgar Rios on 02/11/2017.
 */
@Slf4j
class SenderThread extends Thread{

    int liid
    long msisdn
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
    static def sendPOSTXML(def p_liid, def p_msisdn) {
        /*if(true) {
            log.debug "Respuesta envio $p_liid-$p_msisdn: OK"
            return;
        }*/
        def p_xml = '''
            <svc_init>
            <hdr>
                <client>
                    <id>GPPIPcom</id>
                    <pwd>testpiloto</pwd>
                 </client>
                 <liid>$p_liid</liid>
             </hdr>
             <slir res_type="SYNC">
                 <msids>
                     <msid>
                         $p_msisdn
                     </msid>
                 </msids>
                 <loc_type type="CURRENT"/>
              </slir>
        </svc_init>'''
        p_xml = p_xml
                .replace('$p_liid',"$p_liid")
                .replace('$p_msisdn',"$p_msisdn")

        def http = new HTTPBuilder('http://192.168.5.39/LIGMLC/lilcs')
        http.contentType = ContentType.TEXT
        http.headers = [Accept: 'application/xml', charset: 'UTF-8']

        http.request(Method.POST) {
            body = p_xml

            response.success = { resp, reader ->
                //log.debug "Content-Type: ${resp.headers.'Content-Type'}"
                log.debug "Respuesta envio $p_liid: "+reader.text
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

    static def sendPOSTJSON(def p_warrant, int typeSend=1) {
        /*if(true) {
            log.debug "Respuesta activation $p_liid-$p_msisdn: OK"
            return;
        }*/

        def tramaActivation = '''{
            order_type: "activation",
            activationid: "$p_warrantid",
            lemfAddress: "$p_lemfAddress",
            lemfPort: "$p_lemfPort",
            liid: "$p_liid",
            targetid: "$p_msisdn",
            ftpuser: "$p_ftpUser",
            ftppass: "$p_ftpPass"}'''
        tramaActivation = tramaActivation
                            .replace('$p_warrantid',"$p_warrant.id")
                            .replace('$p_liid',"$p_warrant.liid")
                            .replace('$p_msisdn',"$p_warrant.msisdn")
                .replace('$p_lemfAddress',"$p_warrant.lemf_ip")
                .replace('$p_lemfPort',"$p_warrant.lemf_port")
                .replace('$p_ftpUser',"$p_warrant.ftp_user")
                .replace('$p_ftpPass',"$p_warrant.ftp_pass")
        log.debug tramaActivation

        def tramaDeactivation = '''{
                order_type : "deactivation",
                activationid : "$p_warrantid"}'''
        tramaDeactivation = tramaDeactivation
                .replace('$p_warrantid',"$p_warrant.id")

        def http = new HTTPBuilder('http://192.168.5.133:8080/LIInterpreter/Interpreter')
        http.contentType = ContentType.TEXT

        http.request( Method.POST) { req ->
            body = typeSend==1?tramaActivation:tramaDeactivation

            response.success = { resp, reader ->
                //log.debug "Content-Type: ${resp.headers.'Content-Type'}"
                log.debug "Respuesta li-interpreter $p_liid: "+reader.text
                //log.debug "$resp.statusLine   Respond rec"
            }
        }
    }
}

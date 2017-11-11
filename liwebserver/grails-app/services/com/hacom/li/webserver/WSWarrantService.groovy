package com.hacom.li.webserver

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Created by Edgar Rios on 08/11/2017.
 * https://spring.io/guides/gs/circuit-breaker/
 */
@Service
class WSWarrantService {

    RestTemplate restTemplate = new RestTemplate();

    String echo(String text) {
        String responseWSManager = restTemplate.getForObject("http://localhost:9090/wsmanager/echo/{text}",String.class,text)
        return responseWSManager
    }

    @HystrixCommand(fallbackMethod = "reliable")
    String find(String p_liid) {
        println p_liid
        //URI uri = URI.create()
        String responseWSManager = restTemplate.getForObject("http://localhost:9090/wsmanager/find/{liid}",String.class,p_liid)
        return responseWSManager
    }

    public String reliable(String liid) {
        return "ERR_CONN";
    }
}

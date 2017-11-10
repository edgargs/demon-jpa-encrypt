package com.hacom.li.manager

import com.hacom.li.util.LIEncryption
import com.hacom.li.util.SenderThread
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@Slf4j
class ManagerApplication {

	static void main(String[] args) {
		SpringApplication.run ManagerApplication, args

		//MultiServer.main()
	}

	@Bean
	public CommandLineRunner manager(WarrantRepository repository) {
		LIEncryption.keyEncrypt = ConfigManager.keyEncrypt
		def timeRefresh = 30

		while(true) {

			def countWarrants = repository.findByStatus(1)
			Date now = new Date()

			// Verifica hilos ejecutandose
			countWarrants?.each { it ->
				if (now.after(it.begin_datetime) && now.before(it.end_datetime)) {
					// Verifica activacion
					if (it.activation == 0) {
						SenderThread.sendPOSTJSON(it)
						it.activation = 1
						it.lastUpdated = new Date()
						repository.activation(it.activation,it.lastUpdated,it.id)
					}
					//log.debug "now: $now"
					log.debug "ManagerApplication=$it.liid:$it.period"

					verifyProcessLI(Integer.parseInt(it.liid),
									Integer.parseInt(it.period),
									Long.parseLong(it.msisdn) );
				}
			}
			//Desactiva hilos
			countWarrants?.each { it ->
				if (now.after(it.end_datetime)) {

					// Verifica activacion
					if (it.activation == 1) {
						SenderThread.sendPOSTJSON(it,2)
						it.activation = 2
						it.lastUpdated = new Date()
						repository.activation(it.activation,it.lastUpdated,it.id)
					}

					detentionProcess(Integer.parseInt(it.liid) )
					log.info "Outdate $it.liid"
					it.status = 3
					it.lastUpdated = new Date()
					//repository.save(it)
					repository.setComplete(it.status,it.lastUpdated,it.id)
				}
			}

			sleep(timeRefresh*1000)
		}
	}

	static ConcurrentHashMap<Integer,SenderThread> devices = new ConcurrentHashMap<>();

	def verifyProcessLI(int liid, int period, long msisdn) {
		synchronized(devices) {
			SenderThread hilo;

			hilo = devices.get(liid);

			if(hilo == null || hilo.getState() == Thread.State.TERMINATED){
				devices.remove(liid);

				hilo = new SenderThread(liid:liid, period:period, msisdn: msisdn);
				devices.put(liid, hilo);
				hilo.start();
				log.debug("Nuevo proceso: $liid");
			} else {
				log.debug("Proceso en ejecucion: $liid");
			}
		}
	}

	def detentionProcess(int liid) {
		synchronized(devices) {
			SenderThread hilo;

			hilo = devices.get(liid);

			if(hilo != null) {
				hilo.detener = true
			}
		}
	}

}
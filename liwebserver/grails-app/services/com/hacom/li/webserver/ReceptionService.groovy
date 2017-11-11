package com.hacom.li.webserver

import grails.gorm.services.Service

/**
 * Created by Edgar Rios on 10/11/2017.
 */
@Service(Reception)
interface ReceptionService {

    Reception get(Serializable id)

    List<Reception> list(Map args)

    Long count()

    void delete(Serializable id)

    Reception save(Reception reception)
}

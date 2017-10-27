package com.hacom.liguiweb

import grails.gorm.services.Service

@Service(WarrantEncrypt)
interface WarrantEncryptService {

    WarrantEncrypt get(Serializable id)

    List<WarrantEncrypt> list(Map args)

    Long count()

    void delete(Serializable id)

    WarrantEncrypt save(WarrantEncrypt warrantEncrypt)

}
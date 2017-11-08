package com.hacom.li.manager

import com.hacom.li.util.LIEncryption

import javax.persistence.PostLoad
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

/**
 * Created by Edgar Rios on 30/10/2017.
 * https://www.concretepage.com/java/jpa/jpa-entitylisteners-example-with-callbacks-prepersist-postpersist-postload-preupdate-postupdate-preremove-postremove
 */
class WarrantListener {

    @PrePersist
    public void userPrePersist(Warrant ob) {
        System.out.println("Listening User Pre Persist : " + ob.getName());
    }

    @PreUpdate
    public void userPreUpdate(Warrant ob) {

    }

    @PostLoad
    public void userPostLoad(Warrant ob) {
        ob.liid = LIEncryption.decrypt(ob.liid)
        ob.period = LIEncryption.decrypt(ob.period)
        ob.imei = ob.imei?LIEncryption.decrypt(ob.imei):null
        ob.msisdn = LIEncryption.decrypt(ob.msisdn)
    }
}

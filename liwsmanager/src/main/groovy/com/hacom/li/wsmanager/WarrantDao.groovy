package com.hacom.li.wsmanager

import groovy.sql.Sql
import com.hacom.li.util.LIEncryption

/**
 * Created by Edgar Rios on 08/11/2017.
 */
class WarrantDao {

   static def url = 'jdbc:mysql://127.0.0.1:3306/lidb'
    static def user = 'lidbus'
    static def password = 'testing'
    static def driver = 'com.mysql.jdbc.Driver'
    static def sql = Sql.newInstance(url, user, password, driver)

    def searchById(String liid) {
        String p_liid = LIEncryption.encrypt(liid)
        def warrant = sql.firstRow("SELECT * FROM warrant WHERE liid = ?",[p_liid])
        return warrant
    }

}

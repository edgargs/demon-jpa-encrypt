package com.hacom.li.manager

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Edgar Rios on 30/10/2017.
 */
interface WarrantRepository extends JpaRepository<Warrant, Long>{

    List<Warrant> findByStatus(int status)

    @Modifying
    @Query("UPDATE Warrant SET status=?1,lastUpdated=?2 WHERE warrantid=?3")
    @Transactional
    int setComplete(int status, Date lastUpdated, long warrantid)
}

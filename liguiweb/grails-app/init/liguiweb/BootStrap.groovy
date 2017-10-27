package liguiweb

import com.hacom.li.util.LIEncryption
import grails.core.GrailsApplication
import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        def authorities = ['ROLE_USER']
        authorities.each {
            if ( !Role.findByAuthority(it) ) {
                new Role(authority: it).save()
            }
        }
        if ( !User.findByUsername('sherlock') ) {
            def u = new User(username: 'sherlock', password: 'elementary')
            
            u.save()
            def ur = new UserRole(user: u, role:  Role.findByAuthority('ROLE_USER'))
            ur.save()
        }

        def keyEncrypt = grailsApplication.config.getProperty('liguiweb.keyEncrypt')
        LIEncryption.keyEncrypt = keyEncrypt

        log.debug "Init aplication LI-GUI-WEB"
    }
    def destroy = {
    }
}

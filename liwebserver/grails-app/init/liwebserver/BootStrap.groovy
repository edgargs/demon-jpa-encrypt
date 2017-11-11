package liwebserver

import grails.core.GrailsApplication
import com.hacom.li.util.LIEncryption

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        def authorities = ['ROLE_USER']
        authorities.each {
            if ( !Role.findByAuthority(it) ) {
                new Role(authority: it).save()
            }
        }
        if ( !User.findByUsername('hacom') ) {
            def u = new User(username: 'hacom', password: 'elementary')
            
            u.save()
            def ur = new UserRole(user: u, role:  Role.findByAuthority('ROLE_USER'))
            ur.save()
        }

        def keyEncrypt = grailsApplication.config.getProperty('liwebserver.keyEncrypt')
        LIEncryption.keyEncrypt = keyEncrypt
    }
    def destroy = {
    }
}

package liwebserver

class BootStrap {

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
    }
    def destroy = {
    }
}

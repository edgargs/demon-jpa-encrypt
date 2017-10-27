package com.hacom.liguiweb

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import groovy.util.logging.Slf4j

@Slf4j
class WarrantController {

    WarrantEncryptService warrantEncryptService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Warrant> warrantList = new ArrayList<>()
        def warrant
        warrantEncryptService.list(params).each {
            warrant = new Warrant(it.properties)
            warrant.id = it.id
            warrantList.add(warrant)
        }
        respond warrantList, model:[warrantCount: warrantEncryptService.count()]
    }

    def show(Long id) {
        def warrantEncrypt = warrantEncryptService.get(id)
        Warrant warrant = new Warrant(warrantEncrypt.properties)
        warrant.id = warrantEncrypt.id
        respond warrant
    }

    def create() {
        respond new Warrant(params)
    }

    def save(Warrant warrant) {
        if (warrant == null) {
            notFound()
            return
        }
		
        try {
            def warrantEncrypt = new WarrantEncrypt(warrant.properties)
            warrantEncryptService.save(warrantEncrypt)            
            warrant.id = warrantEncrypt.id
        } catch (ValidationException e) {
            respond warrant.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'warrant.label', default: 'Warrant'), warrant.id])
                redirect warrant
            }
            '*' { respond warrant, [status: CREATED] }
        }
    }

    def edit(Long id) {
        def warrantEncrypt = warrantEncryptService.get(id)
        Warrant warrant = new Warrant(warrantEncrypt.properties)
        warrant.id = warrantEncrypt.id
        respond warrant
    }

    //def update(Warrant warrant) {
    def update(Long id, Long version) {
        def warrantEncrypt = warrantEncryptService.get(id)
        if (warrantEncrypt == null) {
            notFound()
            return
        }
        
        Warrant warrant = new Warrant(params)
        warrant.id = warrantEncrypt.id
        try {
            warrantEncrypt.properties = params
            warrantEncryptService.save(warrantEncrypt)
        } catch (ValidationException e) {
            respond warrant.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'warrant.label', default: 'Warrant'), warrant.id])
                redirect warrant
            }
            '*'{ respond warrant, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        warrantEncryptService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'warrant.label', default: 'Warrant'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'warrant.label', default: 'Warrant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

package app

import demo.RandomPersonService
import groovy.transform.CompileStatic

@CompileStatic
class PersonController {
    RandomPersonService randomPersonService = new RandomPersonService()
    def index() {
        render randomPersonService.randomOciPersonName()
    }
}
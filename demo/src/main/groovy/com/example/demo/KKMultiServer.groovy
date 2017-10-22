package com.example.demo

import demo.RandomPersonService

class KKMultiServer {

	static void main(String[] args) {
		RandomPersonService randomPersonService = new RandomPersonService()
		while(true) {
			println randomPersonService.randomOciPersonName()
			sleep(3000)
		}
	}
}
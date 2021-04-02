package com.mihey.personwatcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonWatcherApplication

fun main(args: Array<String>) {
	runApplication<PersonWatcherApplication>(*args)
}

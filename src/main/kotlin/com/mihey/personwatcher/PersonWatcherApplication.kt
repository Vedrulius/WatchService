package com.mihey.personwatcher

import com.mihey.personwatcher.service.impl.PersonServiceImpl
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonWatcherApplication(private val personService: PersonServiceImpl) : CommandLineRunner {

    override fun run(vararg args: String?) {
       personService.watchingForPersons()
    }
}

fun main(args: Array<String>) {
    runApplication<PersonWatcherApplication>(*args)
}


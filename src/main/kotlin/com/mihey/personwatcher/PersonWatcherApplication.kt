package com.mihey.personwatcher

import com.mihey.personwatcher.service.impl.WatchServiceImpl
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonWatcherApplication(private val watchService: WatchServiceImpl) : CommandLineRunner {

    override fun run(vararg args: String?) {
       watchService.watching()
    }
}

fun main(args: Array<String>) {
    runApplication<PersonWatcherApplication>(*args)
}


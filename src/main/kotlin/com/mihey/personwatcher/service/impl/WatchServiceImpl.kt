package com.mihey.personwatcher.service.impl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.*

@Service
class WatchServiceImpl(private val personService: PersonServiceImpl) {

    @Value("\${watchservice.path}")
    lateinit var filePath: String
    private val logger = LoggerFactory.getLogger(javaClass)

    fun watching() {
        try {
            val watchService: WatchService = FileSystems.getDefault().newWatchService()
            val path: Path = Paths.get(filePath)
            val key: WatchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE)
            while (true) {
                for (pollEvent in key.pollEvents()!!) {
                    personService.getPersonsFromFile(path.resolve(pollEvent.context() as Path).toFile())
                }
            }
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }
}
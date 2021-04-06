package com.mihey.personwatcher.service.impl

import com.google.gson.Gson
import com.mihey.personwatcher.model.Person
import com.mihey.personwatcher.repository.PersonRepository
import com.mihey.personwatcher.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.*

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val gson = Gson()
    private val watchService: WatchService = FileSystems.getDefault().newWatchService()

    @Value("\${watchservice.path}")
    lateinit var filePath: String

    fun watchingForPersons() {
        try {
            val path: Path = Paths.get(filePath)
            val key: WatchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE)
            while (true) {
                for (pollEvent in key.pollEvents()!!) {
                    getPersonsFromFile(path.resolve(pollEvent.context() as Path).toFile())
                }
            }
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }

    private fun getPersonsFromFile(file: File) {
        val personsJson: String = file.readLines().joinToString(separator = "")
        try {
            val persons: List<Person> = gson.fromJson(personsJson, Array<Person>::class.java).toList()
            for (person in persons) {
                if (isPersonPresent(person.name, person.lastName)) {
                    logger.info("ignoring Person{ ${person.name}, ${person.lastName} }")
                } else {
                    savePerson(person)
                }
            }
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }

    override fun isPersonPresent(name: String, lastName: String): Boolean {
        return personRepository.findPersonByFullName(name, lastName) != null
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }
}
package com.mihey.personwatcher.service.impl

import com.google.gson.Gson
import com.mihey.personwatcher.model.Person
import com.mihey.personwatcher.repository.PersonRepository
import com.mihey.personwatcher.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.io.File

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getPersonsFromFile(file: File) {
        val personsString: String = file.readLines().joinToString(separator = "")
        val gson = Gson()
        try {
            val persons: List<Person> = gson.fromJson(personsString, Array<Person>::class.java).toList()
            for (person in persons) {
                if (isPersonPresent(person.name, person.lastName)) {
                    logger.info("Ignoring Person{ ${person.name} ${person.lastName} }")
                } else {
                    savePerson(person)
                }
            }
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }

    override fun getPersons(): List<Person> {
        return personRepository.findAll()
    }

    override fun isPersonPresent(name: String, lastName: String): Boolean {
        return personRepository.findPersonByFullName(name, lastName) != null
    }

    override fun findById(id: Int): Person? {
        return personRepository.findByIdOrNull(id)
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun updatePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun deleteById(id: Int) {
        personRepository.deleteById(id)
    }
}
package com.mihey.personwatcher.service.impl

import com.mihey.personwatcher.model.Person
import com.mihey.personwatcher.repository.PersonRepository
import com.mihey.personwatcher.service.PersonService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {

    override fun getPersons(): List<Person> {
        return personRepository.findAll()
    }

    override fun findById(id: Int): Person? {
        return personRepository.findByIdOrNull(id)
    }

    override fun savePerson(person: Person) {
        personRepository.save(person)
    }

    override fun updatePerson(person: Person) {
        personRepository.save(person)
    }

    override fun deleteById(id: Int) {
        personRepository.deleteById(id)
    }
}
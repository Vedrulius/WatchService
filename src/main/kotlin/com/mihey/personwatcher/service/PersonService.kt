package com.mihey.personwatcher.service

import com.mihey.personwatcher.model.Person

interface PersonService {

    fun getPersons(): List<Person>
    fun findById(id: Int): Person?
    fun savePerson(person: Person)
    fun updatePerson(person: Person)
    fun deleteById(id: Int)
}
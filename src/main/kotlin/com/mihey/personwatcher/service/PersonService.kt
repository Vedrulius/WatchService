package com.mihey.personwatcher.service

import com.mihey.personwatcher.model.Person

interface PersonService {

    fun savePerson(person: Person): Person
    fun isPersonPresent(name: String, lastName: String): Boolean
}
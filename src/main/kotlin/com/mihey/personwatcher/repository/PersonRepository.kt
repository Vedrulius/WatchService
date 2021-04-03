package com.mihey.personwatcher.repository

import com.mihey.personwatcher.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PersonRepository : JpaRepository<Person, Int> {

    @Query("SELECT p FROM Person p WHERE p.name = ?1 and p.lastName = ?2")
    fun findPersonByFullName(name: String, lastName: String): Person?
}
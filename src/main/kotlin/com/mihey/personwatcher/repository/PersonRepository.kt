package com.mihey.personwatcher.repository

import com.mihey.personwatcher.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository:JpaRepository<Person,Int> {

}
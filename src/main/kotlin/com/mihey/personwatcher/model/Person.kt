package com.mihey.personwatcher.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Person(
    @Id
    val id: Int,
    @Column(name = "name")
    val name: String,
    @Column(name = "last_name")
    val lastName: String
)
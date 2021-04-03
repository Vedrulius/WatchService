package com.mihey.personwatcher.model

import javax.persistence.*

@Entity
@Table(name = "persons")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(name = "name")
    val name: String,
    @Column(name = "last_name")
    val lastName: String
)
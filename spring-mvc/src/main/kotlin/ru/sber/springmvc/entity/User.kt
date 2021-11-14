package ru.sber.springmvc.entity

import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var login: String,
    @Column
    var password: String,
    @Column
    var role: String?
)
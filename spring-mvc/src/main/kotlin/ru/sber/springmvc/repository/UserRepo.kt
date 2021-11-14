package ru.sber.springmvc.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.sber.springmvc.entity.User

@Repository
interface UserRepo : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}
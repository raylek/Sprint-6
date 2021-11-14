package ru.sber.springmvc.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.sber.springmvc.entity.User as myUser
import ru.sber.springmvc.repository.UserRepo

@Service
class MyUserDetailsService: UserDetailsService {

    @Autowired
    lateinit var dao: UserRepo

    override fun loadUserByUsername(p0: String): UserDetails {
        val myUser: myUser = dao.findByLogin(p0) ?: throw Exception("User not found")
        val user = User.builder()
            .username(myUser.login)
            .password(myUser.password)
        if (myUser.role != null)
            user.roles(myUser.role)
        else
            user.roles()
        return user.build()
    }
}
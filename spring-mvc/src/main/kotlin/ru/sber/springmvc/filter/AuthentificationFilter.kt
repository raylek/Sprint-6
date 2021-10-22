package ru.sber.springmvc.filter

import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter("/app/*")
class AuthentificationFilter: HttpFilter() {

    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        val cookies = request!!.cookies
        if(cookies == null) {
            response!!.sendRedirect("/login")
            return
        } else {
            for(cookie in cookies) {
                if(cookie.name != "auth") {
                    response!!.sendRedirect("/login")
                    return
                } else if(cookie.value >= LocalDateTime.now().toString()) {
                    response!!.sendRedirect("/login")
                    return
                } else {
                    chain!!.doFilter(request, response)
                }
            }
        }
    }
}
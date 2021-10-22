package ru.sber.springmvc.servlet

import java.time.LocalDateTime
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/login")
class AuthentificationServlet: HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val username = req?.getParameter("username")
        val password = req?.getParameter("password")

        if("admin".equals(username) && "12345".equals(password)) {
            val cookie = Cookie("auth", LocalDateTime.now().toString())
            resp!!.addCookie(cookie)
            resp.sendRedirect("/app/add")
            return
        }
        else {
            resp!!.sendRedirect("/login")
            return
        }
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req!!.getRequestDispatcher("/logining").forward(req, resp)
        return
    }
}
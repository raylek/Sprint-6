package ru.sber.springmvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {

    @RequestMapping("/logining")
    fun loginPage(): String {
        return "/logining"
    }

}
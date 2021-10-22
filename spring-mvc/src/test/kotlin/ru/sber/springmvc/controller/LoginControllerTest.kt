package ru.sber.springmvc.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import javax.servlet.http.Cookie
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import ru.sber.springmvc.controller.MVCControllerTest.Companion.cookieName
import ru.sber.springmvc.controller.MVCControllerTest.Companion.cookieTime


@AutoConfigureMockMvc
@SpringBootTest
internal class LoginControllerTest {

    @Autowired
    lateinit var loginController: LoginController
    lateinit var mvcController: MVCController
    lateinit var restController: RESTController

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun open_page_test() {
        mockMvc.perform(
            get("/app/add")
                .cookie(Cookie(cookieName, cookieTime))
        )
            .andDo(::print)
            .andExpect(view().name("add"))
    }

    @Test
    fun redirect_test() {
        mockMvc.perform(get("/app/add"))
            .andDo(::print)
            .andExpect(status().`is`(302))
            .andExpect(redirectedUrl("/login"))
    }
}
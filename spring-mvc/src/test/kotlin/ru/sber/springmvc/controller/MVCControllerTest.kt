package ru.sber.springmvc.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import ru.sber.springmvc.dataBase.AddressBook
import ru.sber.springmvc.dto.Record
import java.time.LocalDateTime
import javax.servlet.http.Cookie

@AutoConfigureMockMvc
@SpringBootTest
internal class MVCControllerTest() {

    companion object {
        val username = "admin"
        val password = "12345"
        val cookieName = "auth"
        val cookieTime = LocalDateTime.now().minusMinutes(10).toString()
        val nameForRecord = "Jon"
        val addressForRecord = "Some other adress"
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var addressBook: AddressBook

    @BeforeEach
    fun before() {
        addressBook.addRecord(Record(nameForRecord, addressForRecord))
        addressBook.addRecord(Record(nameForRecord, addressForRecord))
    }

    //Когда запускаешь тест отдельно он ОК...
    @Test
    fun getExactRecord() {
        mockMvc.perform(
            get("/app/0/view")
                .cookie(Cookie(cookieName, cookieTime))
        )
            .andDo(::print)
            .andExpect(view().name("list"))
    }

    @Test
    fun getViewAdd() {
        mockMvc.perform(
            get("/app/add")
                .cookie(Cookie(cookieName, cookieTime))
        )
            .andDo(::print)
            .andExpect(view().name("add"))
    }

    @Test
    fun addRecord() {
        mockMvc.perform(
            post("/app/add")
                .param("name", nameForRecord)
                .param("address", addressForRecord)
                .cookie(Cookie(cookieName, cookieTime))

        ).andDo(::print)
            .andExpect(view().name("plug"))
    }

    @Test
    fun getList() {
        mockMvc.perform(get("/app/list")
            .cookie(Cookie(cookieName, cookieTime))
        )
            .andDo(::print)
            .andExpect(view().name("list"))
    }



    @Test
    fun editRecord() {
        mockMvc.perform(
            post("/app/0/edit")
                .param("name", nameForRecord)
                .param("address", addressForRecord)
                .cookie(Cookie(cookieName, cookieTime))
        ).andDo(::print)
            .andExpect(view().name("plug"))
    }

    @Test
    fun deleteRecord() {
        mockMvc.perform(
            post("/app/0/delete")
                .cookie(Cookie(cookieName, cookieTime))
        ).andDo(::print)
            .andExpect(view().name("plug"))
    }
}
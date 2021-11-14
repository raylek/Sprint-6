package ru.sber.springmvc.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.sber.springmvc.dataBase.AddressBook
import ru.sber.springmvc.dto.Record
import java.time.LocalDateTime

@AutoConfigureMockMvc
@SpringBootTest
class MVCControllerTest() {

    companion object {
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

    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun getViewAdd() {
        mockMvc.perform(
            get("/app/add")
        ).andExpect(status().isOk)
    }

    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun addRecord() {
        mockMvc.perform(
            post("/app/add")
                .param("name", nameForRecord)
                .param("address", addressForRecord)
        ).andExpect(status().isOk)
    }

    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun getList() {
        mockMvc.perform(get("/app/list")).andExpect(status().isOk)
    }


    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun editRecordFail() {
        mockMvc.perform(
            post("/app/0/edit")
                .param("name", nameForRecord)
                .param("address", addressForRecord)
        ).andExpect(status().`is`(403))
    }

    @WithMockUser(authorities = ["ROLE_ADMIN"])
    @Test
    fun editRecord() {
        mockMvc.perform(
            post("/app/0/edit")
                .param("name", nameForRecord)
                .param("address", addressForRecord)
        ).andExpect(status().isOk)
    }

    @WithMockUser(authorities = ["ROLE_ADMIN"])
    @Test
    fun deleteRecord() {
        mockMvc.perform(
            post("/app/0/delete")
        ).andExpect(status().isOk)
    }

    @WithMockUser(authorities = ["ROLE_USER"])
    @Test
    fun deleteRecordFailed() {
        mockMvc.perform(
            post("/app/0/delete")
        ).andExpect(status().`is`(403))
    }
}
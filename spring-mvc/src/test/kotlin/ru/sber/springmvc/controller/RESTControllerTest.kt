package ru.sber.springmvc.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.sber.springmvc.controller.MVCControllerTest.Companion.addressForRecord
import ru.sber.springmvc.controller.MVCControllerTest.Companion.nameForRecord
import ru.sber.springmvc.dataBase.AddressBook
import ru.sber.springmvc.dto.Record

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class RESTControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private fun url(s: String): String {
        return "http://localhost:${port}/${s}"
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

    @Test
    fun getListOfRecords() {
        mockMvc.perform(
            get(url("api/list"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(::print)
    }

    @Test
    fun getRecord() {
        mockMvc.perform(
            get(url("api/0/view"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(::print)
    }

    @Test
    fun editRecord() {
        val jsonData = """{
            "name" : "John",
            "address" : "Some street 14"
            }"""

        mockMvc.perform(
            put("/api/0/edit")
                .content(jsonData)
                .contentType(APPLICATION_JSON)
        )
            .andDo(::print)
            .andExpect(status().isOk())
    }

    @Test
    fun addRecord() {
        val jsonData = """{
            "name" : "John",
            "address" : "Some street 14"
            }"""

        mockMvc.perform(
            post("/api/add")
                .content(jsonData)
                .contentType(APPLICATION_JSON)
        )
            .andDo(::print)
            .andExpect(status().isOk())
    }

    @Test
    fun deleteRecord() {
        mockMvc.perform(
            delete("/api/0/delete")
        )
            .andDo(::print)
            .andExpect(status().isOk())
    }
}
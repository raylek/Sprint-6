package ru.sber.springmvc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import ru.sber.springmvc.dto.Record
import ru.sber.springmvc.service.AddressBookService

@RestController
@RequestMapping("/api")
class RESTController @Autowired constructor(val addressBookService: AddressBookService){

    @PostMapping("/add")
    fun addRecord(@RequestBody record: Record) {
        addressBookService.addRecord(record)
    }

    @GetMapping("/list")
    fun getRecord() = addressBookService.getRecord()

    @GetMapping("/{id}/view")
    fun getRecord(@PathVariable("id") id: Int) = ResponseEntity(addressBookService.getRecord(id), HttpStatus.OK)

    @PutMapping("/{id}/edit")
    fun editRecord(@PathVariable("id") id: Int, @RequestBody record: Record) {
        addressBookService.editRecord(id = id, record = record)
    }

    @DeleteMapping("/{id}/delete")
    fun deleteRecord(@PathVariable("id") id: Int) {
        addressBookService.deleteRecord(id)
    }
}
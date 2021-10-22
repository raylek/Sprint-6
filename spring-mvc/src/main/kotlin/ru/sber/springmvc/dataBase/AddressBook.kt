package ru.sber.springmvc.dataBase

import org.springframework.stereotype.Component
import ru.sber.springmvc.dto.Record
import java.util.concurrent.ConcurrentHashMap

@Component
class AddressBook() {
    var listOfRecords: ConcurrentHashMap<Int, Record> = ConcurrentHashMap<Int, Record>()
    var id = 0

    fun addRecord(record: Record) {
        listOfRecords[id] = record
        id++
    }
}
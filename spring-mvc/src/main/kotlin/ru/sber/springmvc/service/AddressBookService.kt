package ru.sber.springmvc.service

import org.springframework.stereotype.Service
import ru.sber.springmvc.dataBase.AddressBook
import ru.sber.springmvc.dto.Record

@Service
class AddressBookService {

    private var addressBook = AddressBook()

    fun addRecord(record: Record) {
        addressBook.addRecord(record)
    }

    fun getRecord() = addressBook.listOfRecords

    fun getRecord(id: Int) = addressBook.listOfRecords[id]

    fun editRecord(id: Int, record: Record) {
        addressBook.listOfRecords[id] = record
    }

    fun deleteRecord(id: Int) {
        addressBook.listOfRecords.remove(id)
    }
}
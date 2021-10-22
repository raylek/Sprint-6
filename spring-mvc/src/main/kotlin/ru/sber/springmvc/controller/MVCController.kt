package ru.sber.springmvc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.sber.springmvc.dto.Record
import ru.sber.springmvc.service.AddressBookService

@Controller
@RequestMapping("/app")
class MVCController @Autowired constructor(val addressBookService: AddressBookService) {

    @RequestMapping("/add", method = [RequestMethod.POST])
    fun addRecord(@ModelAttribute form: Record, model: Model): String {
        addressBookService.addRecord(
            Record(
                name = form.name,
                address = form.address
            ))
        model.addAttribute("action", "Запись добавлена")
        return "plug"
    }

    @RequestMapping("/add", method = [RequestMethod.GET])
    fun addRecordPage(): String {
        return "add"
    }

    @RequestMapping("/list", method = [RequestMethod.GET])
    fun getRecord(model: Model): String {
        val records = addressBookService.getRecord()
        model.addAttribute("records", records)
        return "list"
    }

    @RequestMapping("/{id}/view", method = [RequestMethod.GET])
    fun getRecord(@PathVariable("id") id: Int, model: Model): String {
        val record = addressBookService.getRecord(id)
        model.addAttribute("records", record)
        return "list"
    }

    @RequestMapping("/{id}/edit")
    fun editRecord(@PathVariable("id") id: Int, @ModelAttribute form: Record, model: Model): String {
        addressBookService.editRecord(
            id = id,
            record = Record(
                name = form.name,
                address = form.address
        ))
        model.addAttribute("action", "Запись отредактирована")
        return "plug"
    }

    @RequestMapping("/{id}/edit", method = [RequestMethod.GET])
    fun editRecordPage(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("id", id.toString())
        return "add"
    }

    @RequestMapping("/{id}/delete")
    fun deleteRecord(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("action", "Запись удалена")
        addressBookService.deleteRecord(id)
        return "plug"
    }
}
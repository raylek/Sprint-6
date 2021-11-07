package ru.sber.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
open class SpringMvcApplication

fun main(args: Array<String>) {
	runApplication<SpringMvcApplication>(*args)
}

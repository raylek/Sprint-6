package ru.sber.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

interface ServiceInterface

@Component
class FirstServiceImpl : ServiceInterface {
    override fun toString(): String {
        return "FirstServiceImpl"
    }
}

@Component
class SecondServiceImpl : ServiceInterface {
    override fun toString(): String {
        return "SecondServiceImpl"
    }
}

@Component
class SeveralBeanInjectionService {
    @Autowired
    lateinit var services: ArrayList<ServiceInterface>

    override fun toString(): String {
        return "SeveralBeanInjectionService(services=$services)"
    }

    @PostConstruct
    fun init() {
        services.add(SecondServiceImpl())
    }
}

@Configuration
@ComponentScan("ru.sber.services")
class SeveralServicesConfig {
    @Bean
    fun services(): ArrayList<ServiceInterface> {
        return arrayListOf(FirstServiceImpl())
    }
}
package ru.sber.springmvc.filter

import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletResponse
import javax.servlet.ServletRequest

@WebFilter("/*")
class LogFilter: GenericFilterBean() {

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
        val currentRequest = servletRequest as HttpServletRequest
        val currentResponse = servletResponse as HttpServletResponse
        val requestURL = currentRequest.requestURL
        logger.info("Request URL: $requestURL")
        try {
            chain.doFilter(currentRequest, servletResponse)
        } finally {
            val status = currentResponse.status
            logger.info("Response status: $status")
        }
    }
}
package com.minswon.backend.common.presentation

import com.minswon.backend.common.domain.BaseException
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    // BaseException 처리
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException) {
        log.warn("BaseException: {}", e.message, e)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) {
        log.error("Exception: {}", e.message, e)
    }
}

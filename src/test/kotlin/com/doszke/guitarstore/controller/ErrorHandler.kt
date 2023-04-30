package com.doszke.guitarstore.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(Exception::class)
    fun parseError(exception: Exception): ResponseEntity<String> {
        val body = "{\"error\": \"${exception.message}\"}"
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
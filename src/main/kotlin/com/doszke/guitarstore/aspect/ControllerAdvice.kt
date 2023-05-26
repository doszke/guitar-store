package com.doszke.guitarstore.aspect

import com.doszke.guitarstore.exception.BadRequestException
import com.doszke.guitarstore.exception.ForbiddenException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbidden(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }
}
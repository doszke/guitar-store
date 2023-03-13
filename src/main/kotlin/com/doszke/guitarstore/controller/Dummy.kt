package com.doszke.guitarstore.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Dummy {

    @GetMapping("/")
    fun dummy(): String {
        return "Hello world!"
    }

}
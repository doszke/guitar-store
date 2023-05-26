package com.doszke.guitarstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class GuitarStoreApplication

fun main(args: Array<String>) {
	runApplication<GuitarStoreApplication>(*args)
}

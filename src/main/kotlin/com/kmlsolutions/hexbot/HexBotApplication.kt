package com.kmlsolutions.hexbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class HexBotApplication

fun main(args: Array<String>) {
    SpringApplication.run(HexBotApplication::class.java, *args)
}
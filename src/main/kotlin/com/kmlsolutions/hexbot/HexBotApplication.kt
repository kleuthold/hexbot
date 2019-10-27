package com.kmlsolutions.hexbot

import mu.KotlinLogging
import java.awt.Robot

class HexBotApplication {
    companion object {
        val log = KotlinLogging.logger { }
    }

    fun start() {
        log.info { "starting" }
        val robot = Robot()
        (1..5).forEach {
            robot.delay(1000)
            robot.mouseMove(it*200, it*200)
        }
        log.info("done")
    }
}

fun main() {
    HexBotApplication().start()
}
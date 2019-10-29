package com.kmlsolutions.hexbot

import mu.KotlinLogging
import java.awt.Dimension
import java.awt.Robot
import java.awt.Toolkit


class HexBotApplication {
    companion object {
        val log = KotlinLogging.logger { }
    }

    private val screenReader = GameScreenReader()

    fun run() {
        log.info { "Let's play Superhex.io!" }

        while(true) {
            playOneGame(Robot(), Toolkit.getDefaultToolkit().screenSize)
        }
    }

    private fun playOneGame(robot: Robot, screenSize: Dimension) {
        log.info { "Starting a game!" }
        log.info { "Waiting for a green rectangle to click..." }
        val point = screenReader.waitForGreenRectangle(robot, screenSize)
        log.info { "I found a green rectangle, let me try to click it!" }
        screenReader.clickGreenRectangle(robot, point)
        log.info { "I clicked it, let's see if it goes away..." }
        screenReader.waitForNoGreenRectangle(robot, screenSize)
        log.info { "The green rectangle went away, let's play!" }
        while(true) {
            val x = (0 until screenSize.width).random()
            val y = (0 until screenSize.height).random()
            robot.mouseMove(x, y)
            Thread.sleep(1000)
            if(screenReader.findGreenRectangle(robot, screenSize) != null) break
        }
        log.info { "I see a green rectangle, did I die?" }
    }
}

fun main() {
    HexBotApplication().run()
}
package com.kmlsolutions.hexbot

import mu.KotlinLogging
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit


class HexBotApplication {
    companion object {
        val log = KotlinLogging.logger { }
    }

    fun start() {
        log.info { "starting" }
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        val robot = Robot()

        (1 until 100).forEach { _ ->
            val image = robot.createScreenCapture(Rectangle(0, 0, screenSize.width, screenSize.height))
            val count = ScreenDetector().countPixelsInColorRange(image, 0, 50, 0, 50, 0, 50)
            if(count > 200000) log.info { "found $count dark pixels, i might be looking at the superhexio screen" }
            Thread.sleep(100)
        }

        log.info("done")
    }
}

fun main() {
    HexBotApplication().start()
}
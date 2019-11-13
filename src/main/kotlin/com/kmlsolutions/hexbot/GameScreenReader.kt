package com.kmlsolutions.hexbot

import mu.KotlinLogging
import java.awt.Color
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.Robot
import java.awt.event.InputEvent

class GameScreenReader {
    companion object {
        val log = KotlinLogging.logger { }
    }

    private val screenDetector = ScreenDetector()
    private val greenButtonColorRange = ColorRange(minR = 70, maxR = 90, minG = 165, maxG = 185, minB = 70, maxB = 90)

    fun waitForGreenRectangle(robot: Robot, screenSize: Dimension): Point {
        while(true) {
            log.info { "looking for green rectangle "}
            with(findGreenRectangle(robot, screenSize)) {
                if(this != null) return this
            }
            Thread.sleep(1000)
        }
    }

    fun waitForNoGreenRectangle(robot: Robot, screenSize: Dimension) {
        while(true) {
            with(findGreenRectangle(robot, screenSize)) {
                if(this == null) return
                else (robot.mouseMove(this.x, this.y))
            }
            Thread.sleep(100)
        }
    }

    fun findGreenRectangle(robot: Robot, screenSize: Dimension): Point? {
        val image = robot.createScreenCapture(Rectangle(0, 0, screenSize.width, screenSize.height))
        val point = screenDetector.findRectangleOfColor(
            image,
            greenButtonColorRange,
            width = 20,
            height = 20
        )

        if(point != null) {
            log.info { "Green rectangle has color ${Color(image.getRGB(point.x, point.y))}" }
        }

        return point
    }

    fun clickGreenRectangle(robot: Robot, point: Point) {
        robot.mouseMove(point.x, point.y)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(200)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(200)
    }
}
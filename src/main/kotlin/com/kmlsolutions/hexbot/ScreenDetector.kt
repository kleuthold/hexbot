package com.kmlsolutions.hexbot

import mu.KotlinLogging
import java.awt.Color
import java.awt.image.BufferedImage

class ScreenDetector {

    companion object {
        val log = KotlinLogging.logger { }
    }

    fun findRectangleOfColor(
        image: BufferedImage,
        colorRange: ColorRange,
        width: Int,
        height: Int
    ): Point? {
        for(x in 0 until image.width) {
            for(y in 0 until image.height) {
                var isIt = true
                loop@ for(xx in 0..width) {
                    for (yy in 0..height) {
                        if (!isPixelInRange(Point(x + xx, y + yy), image, colorRange)) {
                            isIt = false
                            break@loop
                        }
                    }
                }
                if(isIt) {
                    return Point(x, y)
                }
            }
        }
        return null
    }

    private fun isPixelInRange(
        point: Point,
        image: BufferedImage,
        cr: ColorRange
    ) : Boolean = with(Color(image.getRGB(point.x, point.y))) {
        red in cr.minR..cr.maxR && green in cr.minG..cr.maxG && blue in cr.minB..cr.maxB
    }
}

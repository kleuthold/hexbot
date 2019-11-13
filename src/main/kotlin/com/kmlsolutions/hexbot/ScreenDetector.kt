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
        for(x in 0 until (image.width - width)) {
            for(y in 0 until (image.height - height)) {
                if (isPixelInRange(Point(x, y), image, colorRange)) {
                    var count = 0
                    for(w in 0 until width) {
                        for(h in 0 until height) {
                            if(isPixelInRange(Point(x+w, y+h), image, colorRange)) {
                                count++
                            }
                        }
                    }
                    val ratio = 1.0 * count / (width * height)
                    if(ratio > 0.7) {
                        return Point(x + width/2, y + height/2)
                    }
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

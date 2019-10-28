package com.kmlsolutions.hexbot

import java.awt.Color
import java.awt.image.BufferedImage

class ScreenDetector {
    fun countPixelsInColorRange(
        image: BufferedImage,
        minR: Int,
        maxR: Int,
        minG: Int,
        maxG: Int,
        minB: Int,
        maxB: Int
    ): Int {
        return (0 until image.width - 1).sumBy { x ->
            (0 until image.height - 1).count { y ->
                with(Color(image.getRGB(x, y))) {
                    red in minR..maxR && green in minG..maxG && blue in minB..maxB
                }
            }
        }
    }
}

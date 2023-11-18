package com.example.drawsurface

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


/**
 * Generates Background patterns for the Drawing surface
 */
object BackgroundGenerator {
    fun dots(width: Int, height:Int):Bitmap {
        val createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(createBitmap)
        val paint = Paint()
        paint.color = Color.BLACK
        paint.alpha = 50
        val wPadding = calcPadding(width, 80)
        val hPadding = calcPadding(height, 80)
        for (i in wPadding..(width -wPadding) step 80) {
            for (j in hPadding..(height -hPadding) step 80) {
                canvas.drawCircle(i.toFloat(), j.toFloat(), 6f, paint)
            }
        }
        return createBitmap
    }

    private fun calcPadding(size: Int, stepSize: Int): Int {
        val amt = size / stepSize
        val patternSize = amt * stepSize
        return (size - patternSize) / 2
    }
}
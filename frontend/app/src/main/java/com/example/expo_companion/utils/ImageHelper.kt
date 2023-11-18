package com.example.expo_companion.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

object ImageHelper {
    /**
     * compresses a bitmap to JPG image and writes it to outputstream
     */
    fun bitmapToStream(bitmap: Bitmap): ByteArrayOutputStream {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        return outputStream
    }
}
package com.example.expo_companion.data

import android.graphics.Bitmap
import android.graphics.Color

/**
 * This class represents an answer. while it is theoretically possible to save multiple pages.
 * neither the ui nor the backend support it
 */
data class Answer(
    var content: MutableList<Bitmap>
){
    companion object {
        /**
         * This is a helper function for development %DEV%
         */
        fun getFilledBitmap(): Bitmap {
            val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(Color.RED)
            return bitmap
        }
    }
}


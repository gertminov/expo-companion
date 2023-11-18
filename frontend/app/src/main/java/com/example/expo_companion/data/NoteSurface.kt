package com.example.expo_companion.data

import android.graphics.Bitmap

/**
 * This interface is shared by all objects that hold a bitmap that can be drawn to eg. SessionQuestion and FreeNotes
 */
interface NoteSurface {
    fun saveBmpAndUpdate(bmp: Bitmap)

    fun getBmp(): Bitmap?
}
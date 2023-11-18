package com.example.expo_companion.data

import android.graphics.Bitmap

object FreeNotes: NoteSurface {
    var content = mutableListOf<Bitmap>()
    override fun saveBmpAndUpdate(bmp: Bitmap) {
        content = mutableListOf(bmp)
    }

    override fun getBmp(): Bitmap? {
        return content.getOrNull(0)
    }
}
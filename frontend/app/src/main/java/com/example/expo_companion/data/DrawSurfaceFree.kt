package com.example.expo_companion.data

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This ensures, that only one canvas tries to unlock RawDrawing at the same time
 */
object DrawSurfaceFree {
    val isFree = MutableStateFlow(true)
}
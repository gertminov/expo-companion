package com.example.expo_companion.ui.content.sharedComponents

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

class CustomVectorRetriever (@DrawableRes val resId: Int) {
    @Composable
    fun asImageVector(): ImageVector {
        return ImageVector.vectorResource(id = resId)
    }
}
package com.example.expo_companion.ui.content.sharedBtns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SecondaryIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    size: Int,
) {
    Button(
        onClick = onClick,
        border = BorderStroke(0.dp, Color.Unspecified),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
        contentPadding = PaddingValues(start = 0.dp, top =0.dp, end = 0.dp, bottom = 0.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "nextBtn",
            modifier = Modifier.size(size.dp),
            tint = Color.Unspecified
        )
    }
}
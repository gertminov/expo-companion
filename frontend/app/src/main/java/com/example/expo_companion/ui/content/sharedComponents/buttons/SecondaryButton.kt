@file:JvmName("SecondaryButtonKt")

package com.example.expo_companion.ui.content.sharedComponents.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.theme.GreyDefault

@Composable
fun SecondaryButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    androidx.compose.material.OutlinedButton(
        onClick = onClick,
        border = BorderStroke(2.dp, GreyDefault),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        contentPadding = PaddingValues(start = 80.dp, top = 20.dp, end = 80.dp, bottom = 20.dp),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}
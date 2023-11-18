package com.example.expo_companion.ui.content.sharedBtns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.theme.GreyDefault

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
//    contentPadding: PaddingValues = PaddingValues(1.dp),
) {
    Button(
        onClick = onClick,
        border = BorderStroke(1.dp, Color.Unspecified),
        colors = ButtonDefaults.buttonColors(backgroundColor = GreyDefault),
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
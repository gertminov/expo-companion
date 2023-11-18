package com.example.expo_companion.ui.content.sharedBtns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.theme.White

@Composable
fun QuestionButton(
    onClick: () -> Unit,
    text: String,
){
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, GreyDefault),
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        contentPadding = PaddingValues(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
        modifier = Modifier.padding(30.dp).width(300.dp).height(90.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}
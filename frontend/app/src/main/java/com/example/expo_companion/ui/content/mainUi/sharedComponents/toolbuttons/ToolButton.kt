package com.example.expo_companion.ui.content.mainUi.sharedComponents.toolbuttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.theme.White



@Composable
 fun ToolButton(
    selected: Boolean,
    tool: ImageVector,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    val color = if (selected) GreyDefault else White
    val bgColor = if (selected) White else GreyDefault
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bgColor
        ),
        onClick = action
    ) {
        Icon(
            imageVector = tool,
            contentDescription = tool.name,
            modifier = modifier
                .size(70.dp),
            tint = color
        )
    }
}







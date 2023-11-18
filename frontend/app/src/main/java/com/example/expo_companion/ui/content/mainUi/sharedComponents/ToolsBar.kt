package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.mainUi.sharedComponents.toolbuttons.ToolButton
import com.example.expo_companion.ui.viewModels.MainUIViewModel

@Composable
fun ToolsBar(
    modifier: Modifier = Modifier,
    viewModel: MainUIViewModel,
) {

    var buttonIndex by remember {
        mutableStateOf(0)
    }


    val tools: List<@Composable (Boolean) -> Unit> = listOf(
        //Pen
        { sel ->
            ToolButton(selected = sel, tool = Icons.Filled.Edit) {
                buttonIndex = 0; viewModel.isPenSelected.value = true
            }
        },
        //Eraser
        { sel ->
            ToolButton(sel, ImageVector.vectorResource(id = R.drawable.eraser_svg)) {
                buttonIndex = 1; viewModel.isPenSelected.value = false
            }
        },
        //Info
        { sel ->
            ToolButton(sel, Icons.Filled.Info) {
                viewModel.openInfoPopUP()
            }
        }
    )



    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .width(IntrinsicSize.Max)
    ) {
        tools.forEachIndexed { index, button ->
            val selected = buttonIndex == index
            button(selected)
        }
    }
}
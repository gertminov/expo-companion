package com.example.expo_companion.ui.content.introduction.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.sharedBtns.SecondaryIconButton
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun QuitAndNextBar(viewModel: ContentPageViewModel, route: String) {

    Box(
        modifier = Modifier
            .padding(30.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(420.dp)
                    .height(90.dp)
                    .padding(start = 30.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                SecondaryIconButton(
                    onClick = { viewModel.closePopUps(); viewModel.goToMainAgain() },
                    imageVector = ImageVector.vectorResource(id = R.drawable.xbtn),
                    size = 89
                )
            }
            Spacer(
                modifier = Modifier
                    .width(450.dp)
                    .background(Color.Green)
            )
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .height(90.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                SecondaryIconButton(
                    onClick = { viewModel.onNextClicked(route) },
                    imageVector = ImageVector.vectorResource(id = R.drawable.nextbtn),
                    size = 90
                )
            }
        }
    }
}
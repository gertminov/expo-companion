package com.example.expo_companion.ui.content.introduction.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.content.introduction.sharedComponents.IntroNavButtons
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun IntroPageBasic(
    viewModel: ContentPageViewModel,
    routeString: String,
    content: @Composable () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .padding(top = 80.dp, start = 80.dp, end = 80.dp, bottom = 0.dp)
                .fillMaxSize()
        ) {
            content()
        }
        IntroNavButtons(viewModel = viewModel, route = routeString)
    }
}

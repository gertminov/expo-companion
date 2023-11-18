package com.example.expo_companion.ui.content.introduction.sharedComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expo_companion.data.IntroFirstCalled
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun IntroNavButtons(viewModel: ContentPageViewModel, route:String) {

    if (!IntroFirstCalled.firstTime.collectAsState().value) {
        QuitAndNextBar(viewModel = viewModel, route = route)
    } else {
        Box(
            modifier = Modifier
                .padding(50.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            NextBtn(viewModel = viewModel, route)
        }
    }
}
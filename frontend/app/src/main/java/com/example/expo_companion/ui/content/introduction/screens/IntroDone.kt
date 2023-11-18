package com.example.expo_companion.ui.content.introduction.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.content.introduction.sharedComponents.IntroNavButtons
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun IntroDone(
    viewModel: ContentPageViewModel,
    routeString: String,
    text: Int
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
//        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(150.dp)
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
            )
        }
        IntroNavButtons(viewModel = viewModel, route = routeString)
    }
}
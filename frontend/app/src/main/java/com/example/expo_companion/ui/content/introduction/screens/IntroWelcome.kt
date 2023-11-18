package com.example.expo_companion.ui.content.introduction.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.sharedComponents.IntroNavButtons
import com.example.expo_companion.ui.content.introduction.sharedComponents.StyledText
import com.example.expo_companion.ui.content.introduction.sharedComponents.textResource
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

/**
 * Just your average Composable, nothing special here.
 */
@Composable
fun IntroWelcome(
    viewModel: ContentPageViewModel,
    nextRoute: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                Text(
                    text = stringResource(id = R.string.home_title),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.home_sub_title),
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(40.dp)
                )
                StyledText(textResource(id = R.string.home_welcome_text))
            }
            IntroNavButtons(viewModel, nextRoute)
        }
    }
}
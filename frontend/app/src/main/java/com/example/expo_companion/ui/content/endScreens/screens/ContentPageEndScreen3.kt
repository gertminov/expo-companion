package com.example.expo_companion.ui.content.endScreens.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.viewModels.EndScreenViewModel


@Composable
fun AllDone(
    viewModel: EndScreenViewModel,
    headerText: Int,
    bodyText: Int) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 300.dp, start = 100.dp, end = 100.dp, bottom = 500.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = headerText),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = bodyText),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
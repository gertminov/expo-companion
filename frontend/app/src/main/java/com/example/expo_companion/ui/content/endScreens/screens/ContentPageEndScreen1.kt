package com.example.expo_companion.ui.content.endScreens.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.sharedComponents.StyledText
import com.example.expo_companion.ui.content.introduction.sharedComponents.textResource
import com.example.expo_companion.ui.viewModels.EndScreenViewModel
import nextBtnEnd
import prevBtnEnd
import xPrevBtnEnd

@Composable
fun EditingDone(
    viewModel: EndScreenViewModel,
    routeStringPrev: String,
    routeStringNext: String,
    headerText: Int,
    bodyText: Int
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(start = 50.dp, bottom = 50.dp)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp, top = 50.dp)
            ) {
                xPrevBtnEnd(viewModel = viewModel, route = routeStringPrev)
            }
            Column(
                modifier = Modifier
                    .padding(start = 50.dp, bottom = 200.dp, top = 50.dp, end = 50.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = headerText),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.tick),
                    contentDescription = "a tick do indicate that the editing is done",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 50.dp),
                    tint = Color.Unspecified
                )
                StyledText(textResource(id = bodyText))
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 50.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(
                        modifier = Modifier
                            .width(400.dp)
                            .height(200.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        prevBtnEnd(viewModel = viewModel, "Zurück zu den Notizen", routeStringPrev)
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(0.5f)
                    )

                    Column(
                        modifier = Modifier
                            .width(400.dp)
                            .height(200.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        nextBtnEnd(viewModel = viewModel, "Bearbeitung beenden", routeStringNext)
                    }
                }
            }
        }
    }
}
package com.example.expo_companion.ui.content.mainUi.screens

import WindowCenterOffsetPositionProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.expo_companion.ui.content.sharedBtns.PrimaryButton
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.theme.White
import com.example.expo_companion.ui.viewModels.MainUIViewModel


@Composable
fun LayoverCenterWindow(viewModel: MainUIViewModel) {
    val topNames = listOf(
        "Freie Notizen",
        "Beobachtung",
        "Reflexion",
        "Erfahrung",
        "Verlorene Fragen",
        "Beantwortung beenden"
    )
    val sideNames = listOf("Stift", "Radiergummi", "Werkzeugtipps anzeigen")
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Popup(
                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                onDismissRequest = { viewModel.closePopup()},
            ) {
                Surface(
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF292929.toInt()).copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxSize()
                ) {
//                        Oben Beschriftung
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(142.dp)
                            .padding(end = 3.dp, start = 3.dp, top = 95.dp, bottom = 1195.dp)
                    ) {
                        for (name in topNames) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Rounded.ArrowUpward,
                                    contentDescription = "arrowUp",
                                    tint = White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .width(10.dp)
                                )
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .background(White)
                                        .padding(end = 2.dp)
                                        .width(175.dp)
                                        .height(68.dp)
                                ) {
                                    Text(
                                        text = name,
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                }
                            }
                        }
                    }
//                        Seite Beschriftung
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .padding(
                                start = 150.dp,
                                end = 934.dp,
                                top = 160.dp,
                                bottom = 1200.dp
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            for (name in sideNames) {
                                Spacer(modifier = Modifier.weight(0.2f))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        Icons.Rounded.ArrowBack,
                                        contentDescription = "arrowBack",
                                        tint = White,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .width(10.dp)
                                    )
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .background(White)
                                            .padding(bottom = 5.dp)
                                            .width(175.dp)
                                            .height(65.dp)
                                            .weight(1f)
                                    ) {
                                        Text(
                                            text = name,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.subtitle2
                                        )
                                    }
                                }
                            }
                        }
                    }
//                        Buttons
                    Column(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(
                                top = 850.dp,
                                bottom = 500.dp,
                                start = 360.dp,
                                end = 360.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        PrimaryButton(
                            onClick = {
                                viewModel.goToIntroductionAgain()
                            },
                            title = "Einführung erneut ansehen",
                            modifier = Modifier.weight(1f).fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.weight(0.5f))
                        PrimaryButton(
                            onClick = {
                                viewModel.closePopup()
                            },
                            title = "Infos schließen",
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
package com.example.expo_companion.ui.content.endScreens.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton
import xPrevBtnEnd

@Composable
fun EndScreenEmail(
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
                    .padding(end = 80.dp, top = 50.dp)
            ) {
                xPrevBtnEnd(viewModel = viewModel, route = routeStringPrev)
            }
            Column(
                modifier = Modifier
                    .padding(start = 50.dp, bottom = 200.dp, top = 50.dp, end = 100.dp)
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
                    imageVector = ImageVector.vectorResource(id = R.drawable.email),
                    contentDescription = "a tick do indicate that the editing is done",
                    modifier = Modifier.size(300.dp),
                    tint = Color.Unspecified
                )
                StyledText(textResource(id = bodyText))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "E-Mail Adresse:",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    OutlinedTextField(
                        value = viewModel.emailFieldValue.value,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        onValueChange = { viewModel.emailFieldValue.value = it },
                        placeholder = {
                            Text(
                                text = "religions@lehrer.de",
                                style = MaterialTheme.typography.body1,
                                color = Color.LightGray
                            )
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .weight(1f)
                    )
                }
                Text(
                    "Ihre Mailadresse wird nur für den Versand Ihrer Notizen verwendet.",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 20.dp)
                )
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
//                        EmailDecisionBtn(
//                            "Nein Danke",
//                        ) { viewModel.noEmailSend(routeStringNext) }
                        SecondaryButton(
                            title = "Nein Danke",
                            onClick = { viewModel.noEmailSend(routeStringNext) },
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(0.5f)
                            .background(Color.Yellow)
                    )

                    Column(
                        modifier = Modifier
                            .width(400.dp)
                            .height(200.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        SecondaryButton(
                            title = "Abschicken",
                            onClick = { viewModel.sendEmailAndNavigate(routeStringNext) },
                        )
                    }
                }
            }
        }
    }
}
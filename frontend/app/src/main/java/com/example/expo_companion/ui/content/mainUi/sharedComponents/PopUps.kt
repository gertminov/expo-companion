package com.example.expo_companion.ui.content.mainUi.sharedComponents

import WindowCenterOffsetPositionProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import com.example.expo_companion.R
import com.example.expo_companion.data.questions.QuestionPair
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.theme.White
import com.example.expo_companion.ui.viewModels.MainUIViewModel
import com.example.expo_companion.ui.content.sharedBtns.PrimaryButton
import com.example.expo_companion.ui.content.sharedBtns.SecondaryIconButton

@Composable
fun PopupCenterWindow(
    accept: () -> Unit,
    decline: () -> Unit,
    content: @Composable () -> Unit
) {
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
                onDismissRequest = decline,
            ) {
                Surface(
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(8.dp),
                    color = White,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 100.dp, top = 50.dp)
                    ) {
                        SecondaryIconButton(
                            onClick = decline,
                            imageVector = ImageVector.vectorResource(id = R.drawable.xbtn),
                            size = 60
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                start = 200.dp,
                                top = 300.dp,
                                end = 200.dp,
                                bottom = 300.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        content()

                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(200.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton(
                                    onClick = decline,
                                    title = "Nein",
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .weight(0.5f)
                            )

                            Column(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(200.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.End
                            ) {
                                PrimaryButton(
                                    onClick = accept,
                                    title = "Ja",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeletePopUpContent(
    questionToDelete: SessionQuestion,
    accept: () -> Unit,
    decline: () -> Unit
) {
    PopupCenterWindow(accept,decline) {
        Text(
            text = stringResource(id = R.string.deletePopup_header),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Text(
            text = questionToDelete.text,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ChangeQuestionPopUpContent(
    questionIcon: Int,
    viewModel: MainUIViewModel,
    questionPair: QuestionPair
) {
    PopupCenterWindow(
        accept = { viewModel.keepNewQuestion(questionPair.newQuestion) },
        decline = viewModel::closePopup
    ) {
        Text(
            text = "Sie haben eine Frage aus der",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = questionIcon),
            contentDescription = "a tick do indicate that the editing is done",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 10.dp),
            tint = Color.Unspecified
        )
        Text(
            text = "Kategorie gescannt.",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Alte Frage: ",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = questionPair.oldQuestion.text,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Neue Frage: ",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Text(
            text = questionPair.newQuestion.text,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Wollen Sie wirklich diese Frage und Ihren Antworttext löschen und durch die neue Frage austauschen?",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}
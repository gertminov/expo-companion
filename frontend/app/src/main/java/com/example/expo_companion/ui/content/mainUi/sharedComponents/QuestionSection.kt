package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton
import com.example.expo_companion.ui.theme.GreyDefault
import kotlinx.coroutines.delay


@Composable
fun QuestionSection(
    question: SessionQuestion,
    onDeleteClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(x = 60.dp)
            .height(380.dp)
            .width(940.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 0.dp)
                .weight(3f)
        ) {
            Text(
                text = question.text,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .offset(y = 30.dp)
                    .align(Alignment.TopStart)
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(200.dp)
                .width(1000.dp)
                .padding(top = 0.dp, start = 40.dp, end = 40.dp, bottom = 5.dp)
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .width(375.dp)
                    .height(150.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                SecondaryButton(
                    title = "Frage löschen",
                    onClick = { onDeleteClicked()},
                    modifier = Modifier
                        .width(373.dp)
                        .height(165.dp)

                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .width(375.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                var showText by remember { mutableStateOf(true) }
                LaunchedEffect(key1 = showText) {
                    delay(2000)
                    showText = true
                }

                Button(
                    onClick = { showText = false },
                    border = BorderStroke(1.dp, Color.Unspecified),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreyDefault),
                    contentPadding = PaddingValues(
                        start = 80.dp,
                        top = 20.dp,
                        end = 80.dp,
                        bottom = 20.dp
                    ),
                    modifier = Modifier
                        .width(373.dp)
                        .height(165.dp)
                ) {
                    if (showText) {
                        Text(
                            text = "Speichern",
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.whitecheck),
                            contentDescription = "a tick do indicate that the editing is done",
                            //  modifier = Modifier.size(300.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .fillMaxWidth()
                .align(Alignment.End)
                .clip(RoundedCornerShape(15.dp)),
            color = Color.Black,
            thickness = 5.dp
        )
    }
}


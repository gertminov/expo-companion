package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.runtime.*
import com.example.expo_companion.data.questions.SessionQuestion


@Composable
fun AnswerSection(
    question: SessionQuestion,
    isPenSelected: MutableState<Boolean>,
) {
    DrawSurface(isPenSelected = isPenSelected, noteSurface = question)
}
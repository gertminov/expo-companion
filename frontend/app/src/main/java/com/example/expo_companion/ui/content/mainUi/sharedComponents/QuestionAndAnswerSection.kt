package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.viewModels.MainUIViewModel

@Composable
fun QuestionAnsAnswerSection(
    viewModel: MainUIViewModel,
    question: SessionQuestion
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        QuestionSection(question, onDeleteClicked = { viewModel.onQuestionDeleteClicked() })
        AnswerSection(question, viewModel.isPenSelected)
    }
}

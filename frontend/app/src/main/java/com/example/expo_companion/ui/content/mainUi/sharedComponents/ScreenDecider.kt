package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.viewModels.MainUIViewModel

@Composable
fun ScreenDecider(
        viewModel: MainUIViewModel,
        question: SessionQuestion?,
        category: Category
) {


    Column(
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = if (question == null) Alignment.End else Alignment.CenterHorizontally
    ) {
        if (question == null) {
            ScanInstruction(category)
        } else {
            QuestionAnsAnswerSection(
                    viewModel,
                    question
            )
        }
    }
}
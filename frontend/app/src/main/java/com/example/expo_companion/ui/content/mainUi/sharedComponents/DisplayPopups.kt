package com.example.expo_companion.ui.content.mainUi.sharedComponents

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.expo_companion.R
import com.example.expo_companion.data.PopUp
import com.example.expo_companion.data.questions.QuestionPair
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.content.mainUi.screens.LayoverCenterWindow
import com.example.expo_companion.ui.viewModels.MainUIViewModel

@Composable
fun DisplayPopups(viewModel: MainUIViewModel) {

    val popup by viewModel.popUpState.collectAsState()
    Log.i("POPUPS", "PopupSateChanged: $popup")

    when (popup) {
        is PopUp.INFO -> LayoverCenterWindow(viewModel)
        is PopUp.DELETE -> DisplayDeletePopup((popup as PopUp.DELETE).question, viewModel)
        is PopUp.REPLACE -> DisplayChangePopUp((popup as PopUp.REPLACE).questionPair, viewModel)
        else -> {}
    }
}

@Composable
private fun DisplayDeletePopup(question: SessionQuestion, viewModel: MainUIViewModel) {
    DeletePopUpContent(
        accept = { viewModel.deleteQuestion(question) },
        decline = viewModel::closePopup,
        questionToDelete = question
    )
}

@Composable
private fun DisplayChangePopUp(questionPair: QuestionPair, viewModel: MainUIViewModel) {
    ChangeQuestionPopUpContent(
        questionIcon = R.drawable.maple_black,
        viewModel = viewModel,
        questionPair
    )
}
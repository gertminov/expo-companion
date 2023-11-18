package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.data.FreeNotes
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.ui.viewModels.DrawingViewModel

@Composable
fun NotesTabBody(
    isPenSelected: MutableState<Boolean>,
    viewModel: DrawingViewModel = hiltViewModel(),
) {
    UsedQuestions.setTabToNotes()

    DrawSurface(isPenSelected = isPenSelected, viewModel = viewModel, noteSurface = FreeNotes)
}
package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.screens.IntroPageOverlay
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

object IntroUsageQuestions : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Question.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) =
        IntroPageOverlay(
            viewModel,
            image = R.drawable.question_background,
            headerText = R.string.questions_title,
            bodyText = R.string.questions_content,
            IntroLostQuestion.route,
            textPositionModifier = Modifier
                .padding(top = 140.dp, start = 100.dp, end = 88.dp, bottom = 0.dp)
                .offset(45.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            spacerModifier = Modifier.fillMaxWidth()
        )
}
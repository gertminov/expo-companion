package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.screens.IntroPageOverlay
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

object IntroLostQuestion : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Question2.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    //TODO: Route ändern
    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.question_background2,
        headerText = R.string.questions_title2,
        bodyText = R.string.questions_content2,
        IntroDone.route
    )
}

package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.screens.IntroPageOverlay
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

object MenuRoute : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Menu1.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.menu_background,
        headerText = R.string.menu_title,
        bodyText = R.string.menu_content,
        Menu2Route.route
    )
}

object Menu2Route : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Menu2.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.menu_background2,
        headerText = R.string.menu_title,
        bodyText = R.string.menu_content2,
        Menu3Route.route
    )
}

object Menu3Route : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Menu3.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    //TODO: Route hier ändern sobald Menu Pages gibt
    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.menu_background3,
        headerText = R.string.menu_title,
        bodyText = R.string.menu_content3,
        IntroUsageQuestions.route
    )
}
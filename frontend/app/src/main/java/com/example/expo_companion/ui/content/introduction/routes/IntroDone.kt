package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.viewModels.ContentPageViewModel
import com.example.expo_companion.ui.content.introduction.screens.IntroDone
import com.example.expo_companion.ui.content.mainUi.routes.MainUIRoute
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute

object IntroDone : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.IntroDone.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroDone(
        viewModel,
        MainUIRoute.route, R.string.introDone_title
    )
}
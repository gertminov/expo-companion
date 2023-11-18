package com.example.expo_companion.ui.content.endScreens.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.endScreens.screens.EndScreenEmail
import com.example.expo_companion.ui.content.mainUi.routes.MainUIRoute
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.EndScreenViewModel

object RouteEndScreen2 : NavRoute<EndScreenViewModel> {

    override val route = HomeScreens.End2.title

    @Composable
    override fun viewModel(): EndScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: EndScreenViewModel) = EndScreenEmail(
        viewModel,
        MainUIRoute.route,
        RouteEndScreen3.route,
        R.string.allDone2_title,
        R.string.allDone2_content
    )
}
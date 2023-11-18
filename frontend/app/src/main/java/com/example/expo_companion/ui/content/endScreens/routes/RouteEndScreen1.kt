package com.example.expo_companion.ui.content.endScreens.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.endScreens.screens.EditingDone
import com.example.expo_companion.ui.content.mainUi.routes.MainUIRoute
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.EndScreenViewModel

object RouteEndScreen1 : NavRoute<EndScreenViewModel> {

    override val route = HomeScreens.End1.title

    @Composable
    override fun viewModel(): EndScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: EndScreenViewModel) = EditingDone(
        viewModel,
        MainUIRoute.route,
        RouteEndScreen2.route,
        R.string.allDone_title,
        R.string.allDone_content
    )
}
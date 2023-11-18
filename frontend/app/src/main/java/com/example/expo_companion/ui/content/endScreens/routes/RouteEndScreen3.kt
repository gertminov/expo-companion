package com.example.expo_companion.ui.content.endScreens.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.endScreens.screens.AllDone
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.EndScreenViewModel

object RouteEndScreen3 : NavRoute<EndScreenViewModel> {

    override val route = HomeScreens.End3.title

    @Composable
    override fun viewModel(): EndScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: EndScreenViewModel) =
        AllDone(viewModel, R.string.allDone3_title, R.string.allDone3_content)
}
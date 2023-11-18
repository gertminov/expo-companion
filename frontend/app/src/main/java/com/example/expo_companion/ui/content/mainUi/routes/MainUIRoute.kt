package com.example.expo_companion.ui.content.mainUi.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.ui.content.mainUi.screens.CreateLayout
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.MainUIViewModel

object MainUIRoute : NavRoute<MainUIViewModel> {

    override val route = HomeScreens.MainUI.title

    @Composable
    override fun viewModel(): MainUIViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: MainUIViewModel) = CreateLayout(viewModel)
}
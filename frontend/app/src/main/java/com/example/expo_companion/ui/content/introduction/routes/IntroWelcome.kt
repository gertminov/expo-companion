package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.ui.viewModels.ContentPageViewModel
import com.example.expo_companion.ui.content.introduction.screens.IntroWelcome
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [com.example.expo_companion.ui.navigation.NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object IntroWelcome : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Welcome.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroWelcome(viewModel, IntroLeaf.route)
}
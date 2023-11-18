package com.example.expo_companion.ui.content.adminUi.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.ui.content.adminUi.screens.AdminInterface
import com.example.expo_companion.ui.content.introduction.routes.IntroWelcome
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.HomeViewModel

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object AdminUiRoute : NavRoute<HomeViewModel> {

    override val route = HomeScreens.AdminUi.title

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) = AdminInterface(viewModel, IntroWelcome.route)

}
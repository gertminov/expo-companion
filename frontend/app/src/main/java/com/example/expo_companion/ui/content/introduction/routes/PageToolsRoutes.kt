package com.example.expo_companion.ui.content.introduction.routes

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.screens.IntroPageOverlay
import com.example.expo_companion.ui.navigation.HomeScreens
import com.example.expo_companion.ui.navigation.NavRoute
import com.example.expo_companion.ui.viewModels.ContentPageViewModel


/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */

object ToolsRoute : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Tools1.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.tools_background,
        headerText = R.string.tools_title,
        bodyText = R.string.tools_content,
        Tools2Route.route
    )
}

object Tools2Route : NavRoute<ContentPageViewModel> {

    override val route = HomeScreens.Tools2.title

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = IntroPageOverlay(
        viewModel,
        image = R.drawable.tools_background2,
        headerText = R.string.tools_title,
        bodyText = R.string.tools_content2,
        MenuRoute.route
    )
}
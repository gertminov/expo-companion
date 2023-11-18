package com.example.expo_companion.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.expo_companion.ui.content.adminUi.routes.AdminUiRoute
import com.example.expo_companion.ui.content.endScreens.routes.RouteEndScreen1
import com.example.expo_companion.ui.content.endScreens.routes.RouteEndScreen2
import com.example.expo_companion.ui.content.endScreens.routes.RouteEndScreen3
import com.example.expo_companion.ui.content.introduction.routes.*
import com.example.expo_companion.ui.content.mainUi.routes.MainUIRoute

enum class HomeScreens(val title: String) {
    AdminUi(title = "adminUi"),
    Welcome(title = "home"),
    PickLeaf(title = "pick_leaf"),
    Tools1(title = "tool1"),
    Tools2(title = "tools2"),
    Menu1(title = "menu"),
    Menu2(title = "menu2"),
    Menu3(title = "menu3"),
    Question(title = "question"),
    Question2(title = "question2"),
    IntroDone(title = "introDone"),
    MainUI(title = "mainUI"),
    End1(title = "end1"),
    End2(title = "end2"),
    End3(title = "end3"),
}

@Composable
fun NavigationComponent(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = AdminUiRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        AdminUiRoute.composable(this,navHostController)
        IntroWelcome.composable(this, navHostController)
        IntroLeaf.composable(this, navHostController)
        ToolsRoute.composable(this, navHostController)
        Tools2Route.composable(this, navHostController)
        MenuRoute.composable(this, navHostController)
        Menu2Route.composable(this, navHostController)
        Menu3Route.composable(this, navHostController)
        IntroUsageQuestions.composable(this, navHostController)
        IntroLostQuestion.composable(this, navHostController)
        IntroDone.composable(this, navHostController)
        MainUIRoute.composable(this, navHostController)
        RouteEndScreen1.composable(this, navHostController)
        RouteEndScreen2.composable(this, navHostController)
        RouteEndScreen3.composable(this, navHostController)
    }
}

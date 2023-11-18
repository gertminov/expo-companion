package com.example.expo_companion.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.ui.content.mainUi.sharedComponents.Tab

@Composable
fun HomeNavGraph(navController: NavHostController,  tabs: List<Tab>) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = UsedQuestions.getCurrentTabCategory().dbName
    ) {
        tabs.forEach { tab ->
            composable(tab.route) {
               tab.Body()
            }
        }
    }
}

object Graph {
    const val HOME = "home_graph"
}
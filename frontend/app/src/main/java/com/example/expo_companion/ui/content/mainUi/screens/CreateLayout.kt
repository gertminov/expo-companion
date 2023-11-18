package com.example.expo_companion.ui.content.mainUi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.ui.content.mainUi.sharedComponents.DisplayPopups
import com.example.expo_companion.ui.content.mainUi.sharedComponents.Tab
import com.example.expo_companion.ui.content.mainUi.sharedComponents.TabBar
import com.example.expo_companion.ui.content.mainUi.sharedComponents.ToolsBar
import com.example.expo_companion.ui.navigation.HomeNavGraph
import com.example.expo_companion.ui.viewModels.MainUIViewModel

@Composable
fun CreateLayout(
    basicViewModel: MainUIViewModel
) {
    val navController = rememberNavController()
    val usedQuestions by UsedQuestions.usedQuestions.collectAsState()
    basicViewModel.tabNavController = navController

    DisplayPopups(viewModel = basicViewModel)

    val tabs: List<Tab> = Tab.getTabs(basicViewModel, usedQuestions)

    Column {
        TabBar(navController = navController, tabs = tabs)
        Box(modifier = Modifier.background(Color.White)) {
            HomeNavGraph(navController = navController, tabs)
            Box(modifier = Modifier.padding(0.dp, 40.dp)) {
                ToolsBar(modifier = Modifier.padding(10.dp), basicViewModel)
            }
        }

    }

}





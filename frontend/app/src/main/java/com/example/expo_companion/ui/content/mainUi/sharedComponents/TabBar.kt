package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.expo_companion.data.TabItem
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.theme.White

@Composable
fun TabBar(
    tabs: List<Tab>,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = GreyDefault
    ) {

        tabs.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }
}

@Composable
fun RowScope.AddItem(
    screen: Tab,
    currentDestination: String?,
    navController: NavHostController
) {
    val selected = screen.route == currentDestination
    val background = if (selected) Modifier.background(White) else Modifier

    BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = if (screen.route == TabItem.Companion.Finish.dbName) Modifier.size(120.dp) else Modifier.size(
                    70.dp
                ),
                tint = if (selected) GreyDefault else White
            )

        },
        selected = selected,
        modifier = background.drawBehind {
            drawLine(
                Color.White,
                Offset(size.width, 0f),
                Offset(size.width, size.height),
                4f
            )
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
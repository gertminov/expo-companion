package com.example.expo_companion.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.expo_companion.data.PopUp
import com.example.expo_companion.data.PopUps
import com.example.expo_companion.ui.content.mainUi.routes.MainUIRoute
import com.example.expo_companion.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ContentPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    init {
        PopUps.state.update { PopUp.INTRO }
    }

    fun onNextClicked(route: String) {
        navigateToRoute(route)
    }

    fun goToMainAgain(){
        navigateToRoute(MainUIRoute.route)
    }

    fun closePopUps() {
        PopUps.state.update { PopUp.NONE }
    }
}
package com.example.expo_companion.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expo_companion.data.Leaf
import com.example.expo_companion.network.AuthApi
import com.example.expo_companion.network.LeafApi
import com.example.expo_companion.network.client.RetroFitClient
import com.example.expo_companion.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator {
    var popUpExchangeLeaf by  mutableStateOf(false)
    var leafs = mutableStateListOf<Leaf>()

    init {
        viewModelScope.launch {
            AuthApi.isAuthorized.collect{isAuth ->
                if (isAuth) {
                    val leafs1 = LeafApi.getLeafs()
                    leafs.addAll(leafs1)

                }
            }
        }
    }

    fun onNextClicked(route: String) {
        navigateToRoute(route)
    }

    fun deleteLeaf(leaf: Leaf) {
        viewModelScope.launch {
            try {
                LeafApi.deleteLeaf(leaf)
            } catch (e: HttpException) {
                Log.e("Network", e.response().toString())
            }
        }
        leafs.remove(leaf)
    }
    fun replaceLeaf(leaf: Leaf) {
        viewModelScope.launch {
            LeafApi.updateLeaf(leaf)
        }
    }
    fun viewModelDecider(){
        if (popUpExchangeLeaf){
            popUpExchangeLeaf = false
        }
    }
}

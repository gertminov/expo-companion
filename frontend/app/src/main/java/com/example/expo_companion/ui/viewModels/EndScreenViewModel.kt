package com.example.expo_companion.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.network.AnswerApi
import com.example.expo_companion.ui.navigation.RouteNavigator
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class EndScreenViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator {


    var emailFieldValue = mutableStateOf(TextFieldValue())

    fun onNextClicked(route: String) {
        navigateToRoute(route)
    }

    fun onBackClicked(route: String) {
        popToRoute(route)
    }

    fun noEmailSend(route: String) {
        //Send answers without sending a mail to the teacher
        viewModelScope.launch {
            AnswerApi.sendAnswers(
                UsedQuestions.usedQuestions.value
            )
        }
        navigateToRoute(route)
    }

    fun sendEmailAndNavigate(route: String) {
        viewModelScope.launch {
            AnswerApi.sendAnswers(
                UsedQuestions.usedQuestions.value,
                emailFieldValue.value.text
            )
        }
        navigateToRoute(route)
    }

//    fun onUpClicked() {
//        navigateUp()
//    }
//
//    fun onNextWithDelayClicked() {
//        viewModelScope.launch {
//            delay(4000)
//            navigateToNextPage()
//        }
//    }

//    fun onIncreaseCounterClicked() {
//        viewState = viewState.copy(counterValue = viewState.counterValue + 1)
//    }
}
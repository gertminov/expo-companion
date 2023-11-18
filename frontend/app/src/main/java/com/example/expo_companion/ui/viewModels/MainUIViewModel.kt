package com.example.expo_companion.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.IntroFirstCalled
import com.example.expo_companion.data.PopUp
import com.example.expo_companion.data.PopUps
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.ui.content.introduction.routes.IntroWelcome
import com.example.expo_companion.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainUIViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance
    private var currentTab = UsedQuestions.scannedQuestionCategory
    val popUpState = PopUps.state
    var tabNavController: NavController? = null
    var isPenSelected = mutableStateOf(true)


    init {
        viewModelScope.launch {
            launch {
                IntroFirstCalled.firstTime.emit(false)
            }
            launch {
                UsedQuestions.replaceQuestionsFlow.collectLatest { pair ->
                    PopUps.state.emit(PopUp.REPLACE(pair))
                }
            }
            launch {
                currentTab.collectLatest { category ->
                    if (category != Category.NOTES)
                        tabNavController?.navigate(category.dbName)
                }
            }
        }
    }

    fun goToIntroductionAgain() {
        navigateToRoute(IntroWelcome.route)
    }

    fun onNextClicked(route: String) {
        navigateToRoute(route)
    }

    fun onQuestionDeleteClicked() {
        viewModelScope.launch {
            val category = UsedQuestions.getCurrentTabCategory()
            val questionToDelete = UsedQuestions.usedQuestions.value[category]
            PopUps.state.emit(PopUp.DELETE(questionToDelete!!))
        }
    }


    fun keepNewQuestion(question: SessionQuestion) {
        UsedQuestions.replaceQuestion(question)
        closePopup()
    }

    fun deleteQuestion(question: SessionQuestion) {
        UsedQuestions.deleteQuestion(question.category)
        closePopup()
    }

    fun closePopup() {
        PopUps.state.update { PopUp.NONE }
    }

    fun openInfoPopUP() {
        PopUps.state.update { PopUp.INFO }
    }
}
package com.example.expo_companion.data

import com.example.expo_companion.data.questions.QuestionPair
import com.example.expo_companion.data.questions.SessionQuestion
import kotlinx.coroutines.flow.MutableStateFlow

object PopUps {
    val state = MutableStateFlow<PopUp>(PopUp.NONE)
}
sealed interface PopUp{
    object INFO:PopUp
    data class DELETE(val question: SessionQuestion):PopUp
    data class REPLACE(val questionPair: QuestionPair):PopUp
    object INTRO: PopUp
    object NONE: PopUp
}

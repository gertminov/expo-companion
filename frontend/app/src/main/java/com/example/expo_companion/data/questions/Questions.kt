package com.example.expo_companion.data.questions

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object Questions : QuestionsInterface {
    override var questions: Map<String, Question> = mapOf()
        /**
         * ensures that the state is updated if new questions are set.
         * if the passed map is empty the state is set to error, because pocketbase
         * sends an empty array if the request has no authorization
         */
        set(value) {
            if (value.isNotEmpty()) {
                state.update { QuestionFetchState.SUCCESS }
            } else {
                state.update { QuestionFetchState.ERROR }
            }
            field = value
        }

    override var state: MutableStateFlow<QuestionFetchState> =
        MutableStateFlow(QuestionFetchState.LOADING)
}



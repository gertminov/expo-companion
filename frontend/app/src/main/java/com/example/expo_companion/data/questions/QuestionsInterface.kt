package com.example.expo_companion.data.questions

import kotlinx.coroutines.flow.MutableStateFlow

interface QuestionsInterface {
    var questions: Map<String, Question>
    var state: MutableStateFlow<QuestionFetchState>
}
package com.example.expo_companion.data.questions

import com.example.expo_companion.data.Category
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Used for development imitates the Map of questions from the server
 */
object OfflineQuestions : QuestionsInterface by Questions {
    override var questions: Map<String, Question> = mapOf(
        "05CAE05C" to Question("Experience Frage", "1234", Category.EXPERIENCE),
        "2" to Question("Reflexion Frage", "2222", Category.REFLEXION),
        "3" to Question("Observation Frage", "3333", Category.OBSERVATION),
        "4" to Question("Hidden Frage", "4444", Category.HIDDEN),
    )

    val leafIds = listOf(
        "05AFAA5C",
        "05B0595C",
        "05BC355C",
        "05B8205C",
        "05C7735C"
    )
    override var state: MutableStateFlow<QuestionFetchState> = MutableStateFlow(QuestionFetchState.SUCCESS)
}
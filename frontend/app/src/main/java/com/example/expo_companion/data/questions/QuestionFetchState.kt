package com.example.expo_companion.data.questions

///**
// * State of Question fetching
// */
//sealed interface QuestionFetchState {
//    /**
//     * Loading state
//     */
//    object LOADING : QuestionFetchState {
//        override val title: String = "Loading questions"
//    }
//
//    /**
//     * Error state. gets displayed if loading wasn't successful
//     */
//    object ERROR : QuestionFetchState {
//        override val title: String = "Error loading questions"
//    }
//
//    data class SUCCESS(
//        val questions: Map<String, Question>,
//        override val title: String = questions.values
//            .joinToString(",") { question -> question.text }
//    ) : QuestionFetchState
//
//    val title: String
//}

enum class QuestionFetchState(val title: String){
    LOADING("Loading questions"),
    ERROR("Error loading questions"),

    /**
     * You gotta get the actual questions from [Questions.questions]
     */
    SUCCESS("Success loading questions")

}


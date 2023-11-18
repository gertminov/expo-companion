package com.example.expo_companion.data.questions

import com.example.expo_companion.data.Answer
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.PopUp
import com.example.expo_companion.data.PopUps
import com.example.expo_companion.network.ErrorApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import android.util.Log

object UsedQuestions {
    private val _usedQuestions: MutableStateFlow<Map<Category, SessionQuestion>> =
        MutableStateFlow(emptyMap())
    val usedQuestions: StateFlow<Map<Category, SessionQuestion>> = _usedQuestions

    fun setQuestion(question: Question) {
        val sessionQuestion = SessionQuestion(question)
        _usedQuestions.update { old -> old + (question.category to sessionQuestion) }
    }



    fun deleteQuestion(category: Category) {
        _usedQuestions.update { old -> old - category }
    }

    fun replaceQuestion(question: SessionQuestion) {
        _usedQuestions.update { old -> old + (question.category to question) }
    }


    /**
     * Handles a new NFC tag intent by loading a new question from the question map,
     * and emitting a new question pair to the
     * questions flow if the new question is different from the previous one.
     *
     * If a pop-up is currently open, no new question is loaded.
     *
     * @param tagId The ID of the NFC tag that was scanned.
     * @return Nothing. This function suspends execution until the new question has
     * been emitted to the flow or an error has occurred.
     * @throws Error if there was an error getting the question from the question
     * map.
     */
    suspend fun onNewNfcIntent(tagId: String) {
        // If popup is open, no new question should be loaded, because it crashes the DrawSurface
        if (PopUps.state.value != PopUp.NONE) return

        kotlin.runCatching {
            // Get the question from the question map, or return if it doesn't exist
            val question = Questions.questions[tagId] ?: return@runCatching
            val oldSessionQuestion = usedQuestions.value[question.category]
            val newSessionQuestion = SessionQuestion(question)

            when {
                oldSessionQuestion == null -> {setQuestion(newSessionQuestion); Log.i("NFC_INTENT","SetQuestion")}
                newSessionQuestion.id == oldSessionQuestion.id -> {Log.i("NFC_INTENT", "Same id"); return@runCatching}
                else -> { Log.i("NFC_INTENT", "replate questions")
                    replaceQuestionsFlow.emit(
                        QuestionPair(oldSessionQuestion, newSessionQuestion)
                    )
                }
            }
            changeTab(newSessionQuestion.category)
        }.onFailure {
            ErrorApi.sendError("Error while getting question from QuestionMap with TagId: $tagId error: $it")
        }
    }

    val replaceQuestionsFlow = MutableSharedFlow<QuestionPair>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )


    //    private val _scannedQuestionCategory = MutableSharedFlow<Category>(1, 1, BufferOverflow.DROP_OLDEST)
//    val scannedQuestionCategory: SharedFlow<Category> = _scannedQuestionCategory
    private val _scannedQuestionCategory = MutableStateFlow(Category.REFLEXION)
    val scannedQuestionCategory: StateFlow<Category> = _scannedQuestionCategory

    fun getCurrentTabCategory(): Category {
        return scannedQuestionCategory.value
    }


    private suspend fun changeTab(cat: Category) {
        _scannedQuestionCategory.emit(cat)
    }

    fun setTabToNotes() {
        _scannedQuestionCategory.update { Category.NOTES }
    }


    fun fillUsedQuestions(questionFromApi: Map<String, Question>) {
        val grouped = questionFromApi.values.groupBy { question -> question.category }

        val sessionQuestion = SessionQuestion(grouped[Category.EXPERIENCE]!![0])
        sessionQuestion.answer.content.add(Answer.getFilledBitmap())

        val mapOf = mapOf(
            Category.EXPERIENCE to sessionQuestion,
            Category.REFLEXION to (SessionQuestion(
                grouped[Category.REFLEXION]!![0]
            )),
            /*Category.OBSERVATION to (SessionQuestion(
                grouped[Category.OBSERVATION]!![0]
            ))*/
        )

        _usedQuestions.update { mapOf }
    }

}


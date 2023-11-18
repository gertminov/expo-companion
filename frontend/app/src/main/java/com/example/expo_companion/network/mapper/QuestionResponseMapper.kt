package com.example.expo_companion.network.mapper

import android.util.Log
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.questions.Question
import com.example.expo_companion.data.questions.SessionQuestion

/**
 * holds methods to map JSON Responses to the classes used in this project
 */
internal object QuestionResponseMapper {
    /**
     * Maps the parsed JSON Response from collections/question/records?expand=category
     * list of [Question]
     */
    fun fromApi(response: QuestionsApiResponse): List<Question> {
        return response.items.map { apiQuestion ->
            Question(
                apiQuestion.text,
                apiQuestion.id,
                apiQuestion.expand.category
            )
        }
    }

    fun fromMappedApi(res: Map<String, MappedApiQuestion>): Map<String, Question> {
        val questions = mutableMapOf<String, Question>()
         res.forEach { (key, value)->
           try {
               questions[key] = Question(
                    id = value.id,
                    text = value.Text,
                    category = Category.fromValueOrNull(value.category)!!
                )
           } catch (e: Exception) {
               Log.e("GetQuestoins", "Error while parsing Category. got category String: ${value.category}")
           }
         }

        return questions

    }




    data class QuestionsApiResponse(
        val items: List<ApiQuestion>
    )

    data class ApiQuestion(
        val text: String,
        val id: String,
        val expand: CategoryExpand
    )

    data class CategoryExpand(
        val category: Category
    )

    data class MappedApiQuestion(
        val id: String,
        val Text: String,
        val category: String
    )
}

package com.example.expo_companion.network

import com.example.expo_companion.data.questions.OfflineQuestions
import com.example.expo_companion.data.questions.Question
import com.example.expo_companion.data.questions.Questions
import com.example.expo_companion.network.client.RetroFitClient
import com.example.expo_companion.network.mapper.QuestionResponseMapper
import retrofit2.http.GET



private interface QuestionApiInterface{
    @GET("collections/question/records?expand=category")
    suspend fun getQuestions(): QuestionResponseMapper.QuestionsApiResponse

    // TODO Needs to be implemented
    @GET("questions")
    suspend fun getMappedQuestions(): Map<String, QuestionResponseMapper.MappedApiQuestion>
}


object QuestionApi{
    private fun getRetroFit(): QuestionApiInterface {
            val retrofitService: QuestionApiInterface by lazy {
                RetroFitClient.getRetroFit().create(QuestionApiInterface::class.java)
            }
            return retrofitService
    }

    /**
     * Returns list of [Question] from the Database
     */
    suspend fun getAllQuestions(): Map<String, Question> {
        val questionsApiResponse = getRetroFit().getQuestions()
        return QuestionResponseMapper.fromApi(questionsApiResponse).mapIndexed{ index, quesiton -> OfflineQuestions.leafIds[index] to quesiton}.toMap()
    }

    suspend fun getQuestions(): Map<String, Question> {
        //TODO replace with real implementation
//        return  OfflineQuestions.questions
        val mappedQuestions = getRetroFit().getMappedQuestions()
        return QuestionResponseMapper.fromMappedApi(mappedQuestions)
    }
}

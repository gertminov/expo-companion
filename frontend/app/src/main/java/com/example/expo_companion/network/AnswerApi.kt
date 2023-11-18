package com.example.expo_companion.network

import android.util.Log
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.Teacher
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.network.client.RetroFitClient
import com.example.expo_companion.utils.ImageHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import java.io.ByteArrayOutputStream


interface AnswerApi {

    @Multipart
    @POST("collections/sessionQuestion/records")
    suspend fun sendAnswer(
        @PartMap partMap: MutableMap<String, RequestBody>,
        @Part files: List<MultipartBody.Part>
    )

    companion object {
        private fun getRetroFit(): AnswerApi {
            val retrofitService: AnswerApi by lazy {
                RetroFitClient.getRetroFit().create(AnswerApi::class.java)
            }
            return retrofitService
        }


        /**
         * Sends the UsedQuestions to the backend
         */
        suspend fun sendAnswers(qAndA: Map<Category, SessionQuestion>) {
            try {
                TeacherApi.createTeacher()
                sendUsedQuestions(qAndA)
            } catch (e: HttpException) {
                Log.i("SendAnswer", "error while trying to send answers to server: " + e.message)
            }
        }

        /**
         * Sends the UsedQuestions to the backend and generates a PDF that is send to the teacher
         */
        suspend fun sendAnswers(qAndA: Map<Category, SessionQuestion>, email: String) {
            var error = ""
            try {
                error = "creat teacher"
                TeacherApi.createTeacher(email)
                error = "send questions and answers"
                sendUsedQuestions(qAndA)
                error = "send mails"
                EMailApi.sendMail(Teacher)
            } catch (e: HttpException) {
                Log.i("SendAnswer", "error while trying to $error to server: " + e.response())
            }
        }

        private suspend fun sendUsedQuestions(qAndA: Map<Category, SessionQuestion>) {
            qAndA.values.forEach { sessionQuestion ->
                val stringRequestBody = createRequestBodyMap(sessionQuestion)
                val answerBitmapStreams = sessionQuestion.answer.content.map { bitmap ->
                    ImageHelper.bitmapToStream(bitmap)
                }
                val answerParts = createMultipartFromStream(answerBitmapStreams)

                getRetroFit().sendAnswer(
                    stringRequestBody,
                    answerParts
                )
            }
        }


        private fun createPartFromString(stringData: String): RequestBody {
            return RequestBody.create(MediaType.parse("text/plain"), stringData)
        }


        /**
         * Creates Multipart parts from files
         * general tutorial to muliparts: https://davidamunga.medium.com/working-with-multipart-form-data-using-retrofit-for-android-280307f23258
         * dynamic amount of images: https://futurestud.io/tutorials/retrofit-2-how-to-upload-a-dynamic-amount-of-files-to-server
         */
        private fun createMultipartFromStream(streams: List<ByteArrayOutputStream>): List<MultipartBody.Part> {
            return streams.map { stream ->
                val requestBody = RequestBody.create(
                    MediaType.parse("image/*"),
                    stream.toByteArray()
                )
                MultipartBody.Part.createFormData(
                    "answer",
                    "testImage.jpg",
                    requestBody
                )
            }
        }

        /**
         * Builds the request body with the additional field for: questionId, questionText and teacherId
         */
        private fun createRequestBodyMap(question: SessionQuestion): MutableMap<String, RequestBody> {
            return mutableMapOf(
                "questionId" to createPartFromString(question.id),
                "questionText" to createPartFromString(question.text),
                "teacherId" to createPartFromString(question.teacher.id)
            )
        }

    }

}





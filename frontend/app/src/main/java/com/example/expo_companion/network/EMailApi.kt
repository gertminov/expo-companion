package com.example.expo_companion.network

import com.example.expo_companion.data.Teacher
import com.example.expo_companion.network.client.RetroFitClient
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST




object EMailApi {

    /**
     * Send eMail with PDF of the questions and answers to the teacher
     */
    suspend fun sendMail(teacher: Teacher) {
        EMailApiInterface.sendEMail(teacher)
    }
}

private interface EMailApiInterface {

    @Headers("Content-Type: application/json")
    @POST("sendmail")
    suspend fun sendMail(@Body teacher: Teacher)

    companion object {
        private fun getRetroFit(): EMailApiInterface {
            val retrofitService: EMailApiInterface by lazy {
                RetroFitClient.getRetroFit().create(EMailApiInterface::class.java)
            }
            return retrofitService
        }
        suspend fun sendEMail(teacher: Teacher) {
            getRetroFit().sendMail(teacher)
        }

    }
}



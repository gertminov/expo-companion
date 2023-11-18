package com.example.expo_companion.network

import com.example.expo_companion.data.Teacher
import com.example.expo_companion.network.client.RetroFitClient
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

object TeacherApi {
    suspend fun createTeacher() {
        TeacherApiService.createTeacher()
    }

    suspend fun createTeacher(email: String) {
        TeacherApiService.createTeacher(email)
    }
}

private interface TeacherApiService {

    @POST("collections/teacher/records")
    suspend fun createTeacher(@Body body: Any = Object()): ApiTeacher

    @Headers("Content-Type: application/json")
    @POST("collections/teacher/records")
    suspend fun createTeacher(@Body email: Map<String, String>): ApiTeacher

    companion object {
        private fun getRetroFit(): TeacherApiService {
            val retrofitService: TeacherApiService by lazy {
                RetroFitClient.getRetroFit().create(TeacherApiService::class.java)
            }
            return retrofitService
        }

        suspend fun createTeacher() {
            val createTeacher = getRetroFit().createTeacher()
            Teacher.id = createTeacher.id
        }

        suspend fun createTeacher(email: String) {
            val emailMap = mapOf("email" to email)
            val createTeacher = getRetroFit().createTeacher(emailMap)
            Teacher.id = createTeacher.id
            Teacher.email = email

        }

        data class ApiTeacher(
            val id: String
        )
    }


}
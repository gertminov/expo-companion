package com.example.expo_companion.network

import com.example.expo_companion.network.client.RetroFitClient
import retrofit2.http.Body
import retrofit2.http.POST


private data class User(
    val identity: String,
    val password: String
)

private data class AuthBody(
    val token:String
)

private interface AuthApiService {
    @POST("collections/users/auth-with-password")
    suspend fun getAuth(@Body user: User): AuthBody
}


object AuthApi {

    val isAuthorized = RetroFitClient.isAuthorized

    suspend fun authUser() {
        if (RetroFitClient.isAuthorized()) {
            return
        }


        val retrofitService: AuthApiService by lazy {
            RetroFitClient.getRetroFit().create(AuthApiService::class.java)
        }
        val user = User(
            Credentials.user,
            Credentials.password
        )
        val token = retrofitService.getAuth(user).token
        RetroFitClient.setAuth(
            token
        )
    }



}
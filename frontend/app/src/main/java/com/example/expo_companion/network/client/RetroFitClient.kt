package com.example.expo_companion.network.client

import com.example.expo_companion.network.mapper.CategoryEnumJsonAdapter
import com.example.expo_companion.network.mapper.TeacherJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//private const val BASE_URL = "http://127.0.0.1:8090//api/"


internal object RetroFitClient {
        private const val BASE_URL = "https://zope.psyergo.uni-wuerzburg.de/api/"
//    private const val BASE_URL = " https://expocompanion2023.pagekite.me/api/"
    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized = _isAuthorized.asStateFlow()

    private val moshi = Moshi.Builder()
        .add(CategoryEnumJsonAdapter())
        .add(TeacherJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor)
//            .addInterceptor(getLogging())
        .build()

    fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
    }

    fun <T> makeApi(apiInterface: Class<T>): Class<T> {
        val retro: Class<T> by lazy {
            getRetroFit().create(apiInterface::class.java)
        }
        return retro
    }


    fun setAuth(token: String) {
        AuthInterceptor.AUTH_TOKEN = token
        _isAuthorized.update { true }
    }

    fun isAuthorized(): Boolean {
        return AuthInterceptor.AUTH_TOKEN != ""
    }


    private fun getLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private object AuthInterceptor : Interceptor {
        var AUTH_TOKEN = ""

        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val builder = original.newBuilder()
                .header("Authorization", AUTH_TOKEN)
            return chain.proceed(builder.build())
        }
    }
}




package com.bignerdranch.android.zd3_2_moskv

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitClient {
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val apiKey = "2d07c869"
    val apiService: OmdbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApiService::class.java)
    }

    fun getApiKey(): String = apiKey
}
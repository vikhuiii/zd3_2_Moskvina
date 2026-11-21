package com.bignerdranch.android.zd3_2_moskv

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {
    @GET(".")
    fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String
    ): Call<MovieResponse>
}
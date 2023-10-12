package com.example.tugasweek9.data.api

import com.example.tugasweek9.data.response.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Routes {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("language") lang: String,
        @Query("page") page: Int,
    ): PopularResponse

}
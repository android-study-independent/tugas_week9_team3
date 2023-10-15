package com.example.tugasweek9.api

import com.example.tugasweek9.response.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int
    ): UpcomingResponse



}
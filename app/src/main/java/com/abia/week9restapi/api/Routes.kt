package com.abia.week9restapi.api

import com.abia.week9restapi.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("languague")lang:String = "en-US",
        @Query("page") page:Int
    ): TopRatedResponse

}
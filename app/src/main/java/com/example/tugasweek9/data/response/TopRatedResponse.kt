package com.example.tugasweek9.data.response

import com.abia.week9restapi.response.MovieResponse
import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    val page: Int,
    val Result : List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages:Int,
    @SerializedName("total_results")
    val totalResult:Int,
)

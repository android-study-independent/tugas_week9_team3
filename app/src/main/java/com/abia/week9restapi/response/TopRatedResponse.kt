package com.abia.week9restapi.response

import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    val page: Int,
    val result : List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages:Int,
    @SerializedName("total_results")
    val totalResult:Int
)

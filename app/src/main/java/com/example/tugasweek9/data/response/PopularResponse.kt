package com.example.tugasweek9.data.response

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<MoviePopularResponse>,
    @SerializedName("total_pages")
    val totalPage : Int,
    @SerializedName("total_results")
    val totalResult: Int
)

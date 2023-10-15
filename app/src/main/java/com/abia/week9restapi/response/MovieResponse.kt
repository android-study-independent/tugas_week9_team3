package com.abia.week9restapi.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id : Int?,
    val overview: Float?,
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val releaseDate: String?,
    val tittle:String?,
    @SerializedName("vote_average")
    val voteAverage: Float?

)

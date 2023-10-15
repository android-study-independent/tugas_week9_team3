package com.example.tugasweek9.data.response

import com.google.gson.annotations.SerializedName

// MENGIDENTIFIKASI DIDALAM DATA OBJECTNYA ATAU MENDFINISIKAN KEY NYA.

// MELIHAT DATA INI SEMUA DARI MANA?
// 1. KE POSTMAN DULU, DI COPY DULU SEGALA MACAM API_KEY ATAU ATHORIZATION NYA
// 2. KLIK BUTTON SEND DAN NANTI AKAN MUNCUL RESPONSENYA, NAH ITU DI COPY HASIL RESPONSE NYA
// 3. KEMUDIAN KE LINK WEB https://jsonviewer.stack.hu/ , DAN PASTE HASILNYA

data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
)

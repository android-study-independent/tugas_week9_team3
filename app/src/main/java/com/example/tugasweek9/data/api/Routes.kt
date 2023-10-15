package com.example.tugasweek9.data.api

import com.example.tugasweek9.data.response.TopRatedResponse
import com.example.tugasweek9.data.response.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query


// UNTUK MENDEKLARASIKAN API YANG AKAN KITA PAKAI

// KATA KUNCI NYA QUERY PARAMS
// NAH CARA LIHATNYA DARI WEB INI https://developer.themoviedb.org/reference/movie-now-playing-list

interface Routes {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int
    ): NowPlayingResponse
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("languague")lang:String = "en_EN",
        @Query("page") page:Int
    ): TopRatedResponse
}
package com.example.tugasweek9.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// HELPER CLASS UNTUK RETROFIT


object Network {

    private fun builder(): Retrofit {
        // create okhttpclient
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        // mengubah data yang tadinya json menjadi sebuah object
        val gson = GsonBuilder().create()

        // create retrofit
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getService(): Routes = builder().create(Routes::class.java)
}
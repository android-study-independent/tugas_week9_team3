package com.example.tugasweek9.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    private val  TOKENKEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTMyNWY4NThhZjAyMmRiMjBhZTg3ZTJiOTRlODMzYyIsInN1YiI6IjY1MjRlMDNjZmQ2MzAwMDBjNTY5ZGJhNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uiUfyr2JiA_STsVNFqeJzAnQpEoAsAfJ1IDIF0WUJNE"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Authorization","Bearer $TOKENKEY ").build()
        return  chain.proceed(request)
    }
}
package com.example.tugasweek9.data.api

import okhttp3.Interceptor
import okhttp3.Response

// INTERCEPTOR ITU UNTUK APA
// INTERCEPTOR ITU SEBAGAI MIDDLEWARE, KETIKA ADA REQUEST DIA AKAN MENAMBAHKAN KE FITUR

class HeaderInterceptor : Interceptor {

    private val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZTZhMTViZDdjMGNkMDFiM2Y2M2M3Zjc1NGQyMzJhZSIsInN1YiI6IjY1MjRkMjMxYjAwNDBhMDEzYTE0NTU5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AztZWX2ZfCYkqUmuXNmvGcny5pOoJJ3xgdIvGhshbb8"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN").build()
        return chain.proceed(request)
    }
}
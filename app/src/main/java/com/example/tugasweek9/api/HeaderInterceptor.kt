package com.example.tugasweek9.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    private val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMjg5ZDM0ZDRlZjljYmFiMTQzYjBjZWE2ODY2OTdmYSIsInN1YiI6IjY1MjRiNmQ0YjAwNDBhMDEwMDJmYzEyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KstAkzSAuMt-KYEXeRtz_xbmd7qSF0Qg7BOT3sRnAUk"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", value = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMjg5ZDM0ZDRlZjljYmFiMTQzYjBjZWE2ODY2OTdmYSIsInN1YiI6IjY1MjRiNmQ0YjAwNDBhMDEwMDJmYzEyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KstAkzSAuMt-KYEXeRtz_xbmd7qSF0Qg7BOT3sRnAUk").build()
        return chain.proceed(request)
    }




}
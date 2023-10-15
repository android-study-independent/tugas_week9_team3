package com.abia.week9restapi.api

import okhttp3.Interceptor
import okhttp3.Response

class Headerinterceptor : Interceptor {
    private  val TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2QxOGQ2NTE2ZTJiMjQ4NWQ5ZWU5Mzg0NGNkNDA0NCIsInN1YiI6IjY1MjUzZTFiMDcyMTY2NDViOTFhNWIzNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wDLMMg0uutvZ1EunrefRyruJ5d2d2gIQMqtAwZvcTCc"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer $TOKEN").build()
        return chain.proceed(request)
    }
}
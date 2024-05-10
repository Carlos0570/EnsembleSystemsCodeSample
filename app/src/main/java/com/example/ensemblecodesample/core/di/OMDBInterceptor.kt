package com.example.ensemblecodesample.core.di

import okhttp3.Interceptor
import okhttp3.Response

class OMDBAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("apikey", "ebce349").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
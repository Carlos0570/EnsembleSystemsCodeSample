package com.example.ensemblecodesample.core.di

import com.example.ensemblecodesample.core.network.OpenMovieDatabaseAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .client(okhttpClient(OMDBAuthInterceptor()))
            .baseUrl(Constants.OMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun okhttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideOpenMovieDbClient(retrofit: Retrofit): OpenMovieDatabaseAPI =
        retrofit.create(OpenMovieDatabaseAPI::class.java)
}

object Constants {
    const val OMDB_BASE_URL = "https://www.omdbapi.com/"
}
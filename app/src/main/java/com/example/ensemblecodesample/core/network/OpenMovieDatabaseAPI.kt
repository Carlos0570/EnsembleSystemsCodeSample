package com.example.ensemblecodesample.core.network

import com.example.ensemblecodesample.core.data.OpenMovieDbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMovieDatabaseAPI {
    @GET("/")
    suspend fun searchItemByTitle(
        @Query("s") title: String,
        @Query("page") page: Int
    ): OpenMovieDbResponse
}
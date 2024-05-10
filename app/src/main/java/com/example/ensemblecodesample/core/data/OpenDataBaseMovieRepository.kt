package com.example.ensemblecodesample.core.data

import com.example.ensemblecodesample.core.network.OpenMovieDatabaseAPI
import javax.inject.Inject

open class OpenDataBaseMovieRepository @Inject constructor(private val openMovieDatabaseAPI: OpenMovieDatabaseAPI) {

    open suspend fun searchItem(title: String, page: Int): OpenMovieDbResponse =
        openMovieDatabaseAPI.searchItemByTitle(title, page)

}
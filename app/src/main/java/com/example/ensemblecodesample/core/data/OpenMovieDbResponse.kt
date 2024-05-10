package com.example.ensemblecodesample.core.data

import com.google.gson.annotations.SerializedName

data class OpenMovieDbResponse(
    @SerializedName("Search")
    var items: ArrayList<Item> = arrayListOf(),

    @SerializedName("totalResults")
    var totalResults: String? = null,

    @SerializedName("Response")
    var response: String? = null
)

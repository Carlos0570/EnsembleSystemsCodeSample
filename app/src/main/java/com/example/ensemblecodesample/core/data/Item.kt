package com.example.ensemblecodesample.core.data

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("Title")
    var title: String? = null,

    @SerializedName("Year")
    var year: String? = null,

    @SerializedName("imdbID")
    var imdbID: String? = null,

    @SerializedName("Type")
    var type: String? = null,

    @SerializedName("Poster")
    var poster: String? = null
)

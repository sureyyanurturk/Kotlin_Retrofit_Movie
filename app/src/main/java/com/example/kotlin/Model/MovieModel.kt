package com.example.kotlin.Model

import com.google.gson.annotations.SerializedName

data class MovieModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("year")
    val year: Int,
    @SerializedName("medium_cover_image")
    val medium_cover_image: String
)







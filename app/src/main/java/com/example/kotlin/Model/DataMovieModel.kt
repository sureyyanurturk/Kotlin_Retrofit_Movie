package com.example.kotlin.Model


import com.google.gson.annotations.SerializedName

data class DataMovieModel(
    @SerializedName("movie")
    val movie: MovieDetailModel
)
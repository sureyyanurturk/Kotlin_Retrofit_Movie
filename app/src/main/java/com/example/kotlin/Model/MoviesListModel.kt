package com.example.kotlin.Model

import com.google.gson.annotations.SerializedName

data class MoviesListModel(

    @SerializedName("movies")
    val movies: List<MovieModel>


)

package com.example.kotlin.Model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("medium_cover_image")
    val medium_cover_image: String,
    @SerializedName("description_intro")
    val description_intro: String,
    val download_count: Int,
    val like_count: Int,
    val language: String


)
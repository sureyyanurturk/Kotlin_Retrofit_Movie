package com.example.kotlin.Api


import com.example.kotlin.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list_movies.json?sort_by=date_added&page=")
    fun getMovies(@Query("page") page: Int): Call<BaseModel<MoviesListModel>>

    @GET("movie_details.json?movie_id=")
    fun getMoviesDetail(@Query("movie_id") movie_id: Int): Call<BaseModel<DataMovieModel>>




}
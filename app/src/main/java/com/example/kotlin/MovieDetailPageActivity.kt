package com.example.kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin.Api.ApiService
import com.example.kotlin.Api.ApiUrl
import com.example.kotlin.Model.BaseModel
import com.example.kotlin.Model.DataMovieModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MovieDetailPageActivity : AppCompatActivity() {

    lateinit var textDetailTitle: TextView
    lateinit var imageDetail: ImageView
    lateinit var textDetailGenres: TextView
    lateinit var textDetailYear: TextView
    lateinit var textDetailIntro: TextView
    lateinit var textDetailRating: TextView
    lateinit var textLanguage: TextView
    lateinit var textLikeCount: TextView
    lateinit var textDownloadCount: TextView
    lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_page)


        textDetailTitle = findViewById(R.id.textDetailTitle)
        imageDetail = findViewById(R.id.imageDetail)
        textDetailGenres = findViewById(R.id.textDetailGenres)
        textDetailYear = findViewById(R.id.textDetailYear)
        textDetailIntro = findViewById(R.id.textDetailIntro)
        textDetailRating = findViewById(R.id.textDetailRating)
        textLanguage = findViewById(R.id.textLanguage)
        textLikeCount = findViewById(R.id.textLikeCount)
        textDownloadCount = findViewById(R.id.textDownloadCount)
        backButton = findViewById(R.id.backButton)

        val bundle: Bundle? = intent.extras
        val id: Int = bundle?.get("id") as Int


        val retrofit: Retrofit = ApiUrl().getClient()
        val apiService2: ApiService = retrofit.create(ApiService::class.java)
        apiService2.getMoviesDetail(id).enqueue(
            object : Callback<BaseModel<DataMovieModel>> {
                override fun onFailure(call: Call<BaseModel<DataMovieModel>>?, t: Throwable?) {
                    Log.e("DetailPageResponse", ": " + t.toString());
                }

                override fun onResponse(
                    call: Call<BaseModel<DataMovieModel>>?,
                    response: Response<BaseModel<DataMovieModel>>?
                ) {
                    textDetailTitle.text = response?.body()?.data?.movie?.title
                    textDetailYear.text = response?.body()?.data?.movie?.year.toString()
                    textDetailGenres.text = response?.body()?.data?.movie?.genres.toString()
                    textDetailIntro.text = response?.body()?.data?.movie?.description_intro
                    textDetailRating.text = response?.body()?.data?.movie?.rating.toString()
                    Picasso.get().load(response?.body()?.data?.movie?.medium_cover_image)
                        .into(imageDetail)
                    textLanguage.text= response?.body()?.data?.movie?.language
                    textLikeCount.text= response?.body()?.data?.movie?.like_count.toString()
                    textDownloadCount.text= response?.body()?.data?.movie?.download_count.toString()
                }
            }
        )

    }

    fun movieDetailBack(view: View) { finish()}
}
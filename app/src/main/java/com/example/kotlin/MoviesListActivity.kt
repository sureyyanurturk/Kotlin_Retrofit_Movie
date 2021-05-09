package com.example.kotlin

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.kotlin.Api.ApiService
import com.example.kotlin.Api.ApiUrl
import com.example.kotlin.Model.BaseModel
import com.example.kotlin.Model.MovieModel
import com.example.kotlin.Model.MoviesListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit



class MoviesListActivity : AppCompatActivity() {

    var page: Int = 1;
    val arrayMovies: MutableList<MovieModel> = ArrayList();
    lateinit var adapter : AdapterRecyclerMovies
    private val progressDialog= CustomProgressDialog()

    lateinit var recyclerViewMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

         adapter = AdapterRecyclerMovies(
            arrayMovies,
            this@MoviesListActivity
        )
        progressDialog.show(this@MoviesListActivity,"Yükleniyor.")
        Handler(Looper.getMainLooper()).postDelayed({
            progressDialog.dialog.dismiss()
        }, 2000)

        recyclerViewMovies = findViewById(R.id.movieRecyclerView)
        recyclerViewMovies.layoutManager = LinearLayoutManager(this@MoviesListActivity)
        recyclerViewMovies.adapter =adapter;
        getData(page)

        recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerViewMovies.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    progressDialog.show(this@MoviesListActivity,"Yükleniyor.")
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressDialog.dialog.dismiss()
                    }, 1000)

                    page++
                    getData(page)

                }
            }

        })
    }
    fun getData(page: Int) {
        val retrofit: Retrofit = ApiUrl().getClient()
        val apiService = retrofit.create(ApiService::class.java).also {

            it.getMovies(page).enqueue(
                object : Callback<BaseModel<MoviesListModel>> {
                    override fun onFailure(call: Call<BaseModel<MoviesListModel>>, t: Throwable?) {
                        Log.e("Response", ": " + t.toString())
                    }

                    override fun onResponse(
                        call: Call<BaseModel<MoviesListModel>>, response: Response<BaseModel<MoviesListModel>>) {



                        arrayMovies.addAll(response.body().data.movies)
                        adapter.list = arrayMovies
                        adapter.notifyDataSetChanged()
                    }
                })
        }
    }
}

      






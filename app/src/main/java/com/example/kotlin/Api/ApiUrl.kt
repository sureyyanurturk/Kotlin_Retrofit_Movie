package com.example.kotlin.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUrl {
    private val BASE_URL="https://yts.mx/api/v2/"

    private lateinit var retrofit: Retrofit;
    fun getClient(): Retrofit {


            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()



        return retrofit
    }

}
package com.example.kotlin.Model

import com.google.gson.annotations.SerializedName


data class BaseModel<T>(

    @SerializedName("data")
    val data: T,
    val status : String
)
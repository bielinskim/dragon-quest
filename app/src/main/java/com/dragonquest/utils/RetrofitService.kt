package com.dragonquest.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    var BASE_URL = "http://192.168.1.105:3000/"

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api: RetrofitApi = retrofit.create(RetrofitApi::class.java)
}
package com.dragonquest.utils

import com.dragonquest.models.Character
import com.dragonquest.models.Quest
import retrofit2.Call;
import retrofit2.http.GET

interface RetrofitApi  {

    @GET("/quest")
    fun getQuests() : Call<List<Quest>>

    @GET("/character")
    fun getCharacters() : Call<List<Character>>

}
package com.dragonquest.utils

import com.dragonquest.models.Character
import com.dragonquest.models.Quest
import com.dragonquest.models.User
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi  {

    @GET("/quest")
    fun getQuests() : Call<List<Quest>>

    @GET("/character")
    fun getCharacters() : Call<List<Character>>

    @POST("/user/register")
    fun registerUser(@Body user : User) : Call<User>

    @POST("/user/login")
    fun loginUser(@Body user : User) : Call<User>

}
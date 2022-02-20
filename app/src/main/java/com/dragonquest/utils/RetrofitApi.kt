package com.dragonquest.utils

import com.dragonquest.models.*
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET("/userCharacter")
    fun getUserCharacters(@Header("Token") token : String) : Call<List<UserCharacter>>

    @GET("/userQuest")
    fun getUserQuests(@Header("Token") token : String) : Call<List<UserQuest>>

    @POST("/userQuest/startQuest")
    fun startQuest(@Header("Token") token : String, @Body startQuest : StartQuest) : Call<StartQuest>

}
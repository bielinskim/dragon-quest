package com.dragonquest.utils

import com.dragonquest.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    var BASE_URL = "http://192.168.1.105:8000/"

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api: RetrofitApi = retrofit.create(RetrofitApi::class.java)

    fun registerUser(user : User, onSuccess: (User?) -> Unit, onError: (String?) -> Unit) {
        api.registerUser(user).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError(null)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                        val addedUser = response.body()
                        onSuccess(addedUser)
                    }
                    if(response.code() == 409) {
                        onError("Login zajęty")
                    }

                }
            }
        )
    }

    fun loginUser(user : User, onSuccess: (User?) -> Unit, onError: (String?) -> Unit) {
        api.loginUser(user).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError(null)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                        val addedUser = response.body()
                        onSuccess(addedUser)
                    }
                    if(response.code() == 400) {
                        onError("Niepoprawne dane logowania")
                    }

                }
            }
        )
    }
}
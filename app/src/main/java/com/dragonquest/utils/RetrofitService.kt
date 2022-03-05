package com.dragonquest.utils

import com.dragonquest.models.Error
import com.dragonquest.models.StartQuest
import com.dragonquest.models.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    var BASE_URL = "http://146.59.44.182:8000/"

    val gson = Gson()

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api: RetrofitApi = retrofit.create(RetrofitApi::class.java)

    fun registerUser(user : User, onSuccess: (User?) -> Unit, onError: (String?) -> Unit) {
        api.registerUser(user).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError("Error while account creating")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if(response.isSuccessful) {
                        val addedUser = response.body()
                        onSuccess(addedUser)
                    } else {
                        val errorJSON = response.errorBody()?.string()
                        val error = gson.fromJson(errorJSON, Error::class.java)
                        onError(error.message)
                    }
                }
            }
        )
    }

    fun loginUser(user : User, onSuccess: (User?) -> Unit, onError: (String?) -> Unit) {
        api.loginUser(user).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError("Error while logging")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                        val addedUser = response.body()
                        onSuccess(addedUser)
                    }
                    if(response.code() == 400) {
                        onError("Incorrect login data")
                    }

                }
            }
        )
    }

    fun startQuest(startQuestData : StartQuest, onSuccess: (StartQuest?) -> Unit, onError: (String?) -> Unit) {
        api.startQuest(UserDataStore.token, startQuestData).enqueue(
            object : Callback<StartQuest> {
                override fun onFailure(call: Call<StartQuest>, t: Throwable) {
                    onError("Error while the quest starting")
                }

                override fun onResponse(call: Call<StartQuest>, response: Response<StartQuest>) {
                    if(response.isSuccessful) {
                        val startedQuest = response.body()
                        onSuccess(startedQuest)
                    }
                    if(response.code() == 400) {
                        onError("Error while the quest starting")
                    }

                }
            }
        )
    }
}
package com.dragonquest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.models.UserCharacter
import com.dragonquest.utils.RetrofitService
import com.dragonquest.utils.UserDataStore
import retrofit2.Call
import retrofit2.Callback

class CharactersViewModel : ViewModel() {

    var characters: MutableLiveData<List<UserCharacter>> = MutableLiveData()

    fun initializeData() {
        val getUsersCharacters = RetrofitService.api.getUserCharacters(UserDataStore.token)

        getUsersCharacters.enqueue( object : Callback<List<UserCharacter>> {
            override fun onResponse(
                call: Call<List<UserCharacter>>,
                response: retrofit2.Response<List<UserCharacter>>
            ) {
                val userCharactersResponse = response.body()
                if(userCharactersResponse != null) {
                    characters.postValue(userCharactersResponse)
                }

            }

            override fun onFailure(call: Call<List<UserCharacter>>?, t: Throwable?) {
                Log.e("RequestError", "getCharacters")
            }
        })
    }
}
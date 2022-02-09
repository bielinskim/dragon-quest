package com.dragonquest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.data.characterImages
import com.dragonquest.models.Character
import com.dragonquest.models.Quest
import com.dragonquest.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback

class CharactersViewModel : ViewModel() {

    var characters: MutableLiveData<List<Character>> = MutableLiveData()

    fun initializeData() {
        val getCharacters = RetrofitService.api.getCharacters()

        getCharacters.enqueue( object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: retrofit2.Response<List<Character>>
            ) {
                val charactersResponse = response.body()
                if(charactersResponse != null) {
                    characters.postValue(charactersResponse)
                }

            }

            override fun onFailure(call: Call<List<Character>>?, t: Throwable?) {
                Log.e("RequestError", "getCharacters")
            }
        })
    }
}
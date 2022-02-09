package com.dragonquest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.models.Level
import com.dragonquest.models.Quest
import com.dragonquest.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback

class QuestsViewModel : ViewModel() {

    var quests: MutableLiveData<List<Quest>> = MutableLiveData()

    fun initializeData() {
        val getQuests = RetrofitService.api.getQuests()

        getQuests.enqueue( object : Callback<List<Quest>> {
            override fun onResponse(
                call: Call<List<Quest>>,
                response: retrofit2.Response<List<Quest>>
            ) {
                val questsResponse = response.body()
                if(questsResponse != null) {
                   quests.postValue(questsResponse)
                }

            }

            override fun onFailure(call: Call<List<Quest>>?, t: Throwable?) {
                Log.e("RequestError", "getQuests")
            }
        })
    }

    fun getUserById(id :Int) : Quest {
        val quest = quests.value?.find { it.id == id }

        if(quest != null) {
            return quest
        } else {
            return Quest()
        }

    }

}
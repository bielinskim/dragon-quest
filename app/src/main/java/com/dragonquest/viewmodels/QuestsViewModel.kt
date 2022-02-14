package com.dragonquest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.models.Quest
import com.dragonquest.models.UserQuest
import com.dragonquest.utils.RetrofitService
import com.dragonquest.utils.UserDataStore
import retrofit2.Call
import retrofit2.Callback

class QuestsViewModel : ViewModel() {

    var quests: MutableLiveData<List<UserQuest>> = MutableLiveData()

    fun initializeData() {
        val getQuests = RetrofitService.api.getUserQuests(UserDataStore.token)

        getQuests.enqueue( object : Callback<List<UserQuest>> {
            override fun onResponse(
                call: Call<List<UserQuest>>,
                response: retrofit2.Response<List<UserQuest>>
            ) {
                val questsResponse = response.body()
                if(questsResponse != null) {
                   quests.postValue(questsResponse)
                }

            }

            override fun onFailure(call: Call<List<UserQuest>>?, t: Throwable?) {
                Log.e("RequestError", "getQuests")
            }
        })
    }

    fun getQuestById(id :Int) : UserQuest {
        val quest = quests.value?.find { it.quest.questId == id }

        if(quest != null) {
            return quest
        } else {
            return UserQuest()
        }

    }

}
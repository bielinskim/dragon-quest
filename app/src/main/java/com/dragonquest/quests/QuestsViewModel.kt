package com.dragonquest.quests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.models.Quest

class QuestsViewModel : ViewModel() {

    var quests: MutableLiveData<List<Quest>> = MutableLiveData()

    fun initializeData() {
        quests.postValue(listOf(
            Quest(1, "Test1", 4,"Desc1"),
            Quest(2, "Test2", 5,"Desc2"),
            Quest(3, "Tes3", 7,"Desc3")
        ))
    }

}
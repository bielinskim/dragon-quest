package com.dragonquest.utils

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.dragonquest.questdetails.CharacterSlot
import com.dragonquest.viewmodels.CharactersViewModel
import com.dragonquest.viewmodels.QuestsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserQuestsUpdater(val chVM: CharactersViewModel, val questVM : QuestsViewModel) {

    fun test() {
        GlobalScope.launch(Dispatchers.Main) {
            repeat(1000) { i ->
                delay(5000L)
                chVM.initializeData()
                questVM.initializeData()
                println("job: I'm sleeping $i ...")
            }
        }
    }
}
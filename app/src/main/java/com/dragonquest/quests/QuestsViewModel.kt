package com.dragonquest.quests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.models.Quest

class QuestsViewModel : ViewModel() {

    var quests: MutableLiveData<List<Quest>> = MutableLiveData()

    fun initializeData() {
        quests.postValue(listOf(
            Quest(0, "Scouts", 1, 2000, 2, "Please excuse me, adventurer. Your assistance is required. We know enemy scouts have managed to infiltrate our camp, they have stolen essential information which could compromise everything we've worked for. I need you to infiltrate their camp and tamper with the documents. Destroy the originals and get rid of the scouts, they might know too much. We'll show those cruel elves. If you don't mind, I'd like to come along. It's your choice though. Tread carefully hero, only a fool would underestimate the elves. If you can, kill as many of them as possible. We want to live in peace and safety. I cannot thank you enough for your help, I'll make sure to reward you greatly once you return. Make haste hero, but tread carefully."),
            Quest(1, "Silverleaf", 3, 3000, 1, "Excuse me, traveler. I'm glad you're here. My husband has fallen ill, he won't wake up no matter what I do. I believe we need to make him drink tea brewed from silverleaf, but they only grow higher up the mountain and I cannot leave my husband's side. Please, hero, gather a handful of silverleaf as quick as you can.Fortunately I have enough left to reward you well if you decide to help me."),
            Quest(2, "Burned village", 4, 5000, 4, "Please, traveler. I'm relieved to see you. They've burned down half our village with wicked sorcery. Hero, I don't know what foul magic they wield, but we cannot let them go unpunished. Please unleash justice upon those miserable lowlives. I had hoped I could you join, but unfortunately I cannot. But I doubt you'd need my help anyway. Be careful hero, don't underestimate those lowlives. Killing them all won't be needed of course, I'm sure your skills will do enough damage to them. I'm happy to say I'll be able to reward you handsomely for your troubles. Now hurry, champion, there's no time to waste. "),
            Quest(3, "Seaweed", 5, 10000, 8, "Pardon me, traveler. I need a favor of you. My brother is such an idiot, he keeps pulling pranks on me and I'm sick and tired of it. It's time for payback and I know the perfect way to do it. My brother is scared of forest spirits, they're not real, but I'm going to make him believe they are. I've made this wooden mask, but I need some more items to make it really scary. Would you mind collect some for me? I need rainbow fowl feathers, some glimmerweed to make the eyes glow and a whole lot of seaweed. Fortunately I have enough left to reward you well if you decide to help me.")
        ))
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
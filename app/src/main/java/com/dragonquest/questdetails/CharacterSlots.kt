package com.dragonquest.questdetails

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.dragonquest.R
import com.dragonquest.data.characterImages
import com.dragonquest.models.Quest
import com.dragonquest.models.Character
import com.dragonquest.models.UserCharacter
import com.dragonquest.models.UserQuest


class CharacterSlots(fragmentView : View, userQuest : UserQuest) {

    private val slotViewIds = arrayOf(R.id.selectedCharacter1, R.id.selectedCharacter2, R.id.selectedCharacter3)
    private val characterSlots = arrayListOf<CharacterSlot>()
    private val charactersImages = characterImages()
    var slots = 0

    init {
        slots = userQuest.quest.slots

        for (i in 0 until slots) {
            val slotView = fragmentView.findViewById<FrameLayout>(slotViewIds[i])

            if(slotView != null) {
                characterSlots.add(CharacterSlot(slotView, null))
                slotView.visibility = View.VISIBLE
            }
        }
    }

    fun selectCharacter(userCharacter : UserCharacter) {
        val isAlreadySelected = characterSlots.any { it.userCharacter?.userCharacterId == userCharacter.userCharacterId }

        if (!isAlreadySelected) {
            val emptyCharacterSlot = characterSlots.firstOrNull { it.userCharacter == null }

            if(emptyCharacterSlot != null) {
                addCharacterToSlot(emptyCharacterSlot, userCharacter)
            }
        }
    }

    fun addCharacterToSlot(characterSlot : CharacterSlot, userCharacter : UserCharacter) {
        characterSlot.userCharacter = userCharacter
        val characterImage = characterSlot.slotView.findViewWithTag<ImageView>("characterImage")
        val image = charactersImages[userCharacter.character.imageId]

        if(image != null) {
            characterImage.setImageResource(image)
            addListenerToFullSlot(characterSlot)
        }
    }

    fun addListenerToFullSlot(characterSlot: CharacterSlot) {
        characterSlot.slotView.setOnClickListener {
            characterSlot.userCharacter = null
            val characterImage = characterSlot.slotView.findViewWithTag<ImageView>("characterImage")
            characterImage.setImageResource(R.drawable.placeholder)
        }
    }

}
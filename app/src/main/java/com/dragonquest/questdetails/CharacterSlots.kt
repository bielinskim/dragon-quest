package com.dragonquest.questdetails

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.dragonquest.R
import com.dragonquest.data.characterImages
import com.dragonquest.models.Quest
import com.dragonquest.models.Character


class CharacterSlots(fragmentView : View, quest : Quest) {

    private val slotViewIds = arrayOf(R.id.selectedCharacter1, R.id.selectedCharacter2, R.id.selectedCharacter3)
    private val characterSlots = arrayListOf<CharacterSlot>()
    private val charactersImages = characterImages()
    var slots = 0

    init {
        slots = quest.slots

        for (i in 0 until slots) {
            val slotView = fragmentView.findViewById<FrameLayout>(slotViewIds[i])

            if(slotView != null) {
                characterSlots.add(CharacterSlot(slotView, null))
                slotView.visibility = View.VISIBLE
            }
        }
    }

    fun selectCharacter(character : Character) {
        val isAlreadySelected = characterSlots.any { it.character?.characterId == character.characterId }

        if (!isAlreadySelected) {
            var emptyCharacterSlot = characterSlots.firstOrNull { it.character == null }

            if(emptyCharacterSlot != null) {
                addCharacterToSlot(emptyCharacterSlot, character)
            }
        }
    }

    fun addCharacterToSlot(characterSlot : CharacterSlot, character : Character) {
        characterSlot.character = character
        val characterImage = characterSlot.slotView.findViewWithTag<ImageView>("characterImage")
        val image = charactersImages[character.imageId]

        if(image != null) {
            characterImage.setImageResource(image)
            addListenerToFullSlot(characterSlot)
        }
    }

    fun addListenerToFullSlot(characterSlot: CharacterSlot) {
        characterSlot.slotView.setOnClickListener {
            characterSlot.character = null
            val characterImage = characterSlot.slotView.findViewWithTag<ImageView>("characterImage")
            characterImage.setImageResource(R.drawable.placeholder)
        }
    }

}
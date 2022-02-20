package com.dragonquest.questdetails

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.dragonquest.R
import com.dragonquest.data.characterImages
import com.dragonquest.models.Quest
import com.dragonquest.models.Character
import com.dragonquest.models.UserCharacter
import com.dragonquest.models.UserQuest
import com.dragonquest.utils.getLevel


class CharacterSlots(fragmentView : View, private val userQuest : UserQuest) {

    private val slotViewIds = arrayOf(R.id.selectedCharacter1, R.id.selectedCharacter2, R.id.selectedCharacter3)
    val characterSlots = arrayListOf<CharacterSlot>()
    private val charactersImages = characterImages()
    private val questDetailsSuccessChance : TextView = fragmentView.findViewById(R.id.questDetailsSuccessChance)
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
            addListenerToSlotWithCharacter(characterSlot)
        }

        setSuccessChance()
    }

    fun addListenerToSlotWithCharacter(characterSlot: CharacterSlot) {
        characterSlot.slotView.setOnClickListener {
            characterSlot.userCharacter = null
            val characterImage = characterSlot.slotView.findViewWithTag<ImageView>("characterImage")
            characterImage.setImageResource(R.drawable.placeholder)
            setSuccessChance()
        }
    }

    fun setSuccessChance() {
        var successChance = 0
        val questLevel = userQuest.quest.level


        for (characterSlot in characterSlots) {
            val experience = characterSlot.userCharacter?.experience

            if(experience != null) {
                val (_, level, _, _) = getLevel(experience)
                var characterSuccessChance = 90

                if(questLevel > level) {
                    val difference = questLevel - level
                    characterSuccessChance -= difference * 15
                }

                if(questLevel < level) {
                    characterSuccessChance = 100
                }

                characterSuccessChance /= characterSlots.size

                successChance += characterSuccessChance

            }
        }

        if(successChance >= 75) {
            questDetailsSuccessChance.setTextColor(Color.parseColor("#28a745"))
        }

        if(successChance in 25..74) {
            questDetailsSuccessChance.setTextColor(Color.parseColor("#ffcc00"))
        }

        if(successChance < 25) {
            questDetailsSuccessChance.setTextColor(Color.parseColor("#dc3545"))
        }

        questDetailsSuccessChance.text = "$successChance % chance"

    }

}
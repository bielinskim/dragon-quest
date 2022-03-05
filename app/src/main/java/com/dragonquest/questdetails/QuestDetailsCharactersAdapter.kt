package com.dragonquest.questdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.data.characterImages
import com.dragonquest.models.Character
import com.dragonquest.models.UserCharacter
import com.dragonquest.utils.GetLevelProgress
import com.dragonquest.utils.getLevel

class QuestDetailsCharactersAdapter(
    private var dataSet: List<UserCharacter> = listOf(),
    private val characterSlots: CharacterSlots
) :
RecyclerView.Adapter<QuestDetailsCharactersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thisView = view
        val characterNameView: TextView = view.findViewById(R.id.characterName)
        val characterImageView: ImageView = view.findViewById(R.id.characterImage)
        val characterExperienceView: ProgressBar = view.findViewById(R.id.characterExperience)
        val characterLevelView: TextView = view.findViewById(R.id.characterLevel)
        val charactersImages = characterImages()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quest_details_character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, experience, character, usersQuests) = dataSet[position]

        var isCharacterAvailable = true

        if (usersQuests != null) {
            for (userQuest in usersQuests) {
                if(userQuest.status == "PENDING") {
                    isCharacterAvailable = false
                    break
                }
            }
        }

        if(isCharacterAvailable) {
            val (_, level, minExp, maxExp) = getLevel(experience)
            val progress = GetLevelProgress(experience, minExp, maxExp)
            val imageId = character.imageId
            val image = holder.charactersImages[imageId]

            holder.characterNameView.text = character.name
            holder.characterExperienceView.progress = progress
            holder.characterLevelView.text = level.toString()
            if(image != null) {
                holder.characterImageView.setImageResource(image)
            }

            holder.thisView.setOnClickListener { it: View ->
                characterSlots.selectCharacter(dataSet[position])
            }
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<UserCharacter>) {
        dataSet = newList
    }

}
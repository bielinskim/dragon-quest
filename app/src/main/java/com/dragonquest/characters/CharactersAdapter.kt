package com.dragonquest.characters

import android.util.Log
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
import com.dragonquest.utils.getLevel
import com.dragonquest.utils.GetLevelProgress


class CharactersAdapter(private var dataSet: List<Character> = listOf()) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterNameView: TextView = view.findViewById(R.id.characterName)
        val characterExperienceView: ProgressBar = view.findViewById(R.id.characterExperience)
        val characterLevelView: TextView = view.findViewById(R.id.characterLevel)
        val characterImageView: ImageView = view.findViewById(R.id.characterImage)
        val charactersImages = characterImages()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = dataSet[position].name
        val experience = dataSet[position].experience
        val imageId = dataSet[position].imageId
        val image = holder.charactersImages[imageId]
        val (_, level, minExp, maxExp) = getLevel(dataSet[position].experience)
        val progress = GetLevelProgress(experience, minExp, maxExp)

        holder.characterNameView.text = name
        holder.characterLevelView.text = level.toString()
        holder.characterExperienceView.progress = progress

        if(image != null) {
                holder.characterImageView.setImageResource(image)
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<Character>) {
        dataSet = newList
    }
}
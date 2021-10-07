package com.dragonquest.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.models.Character

class CharactersAdapter(private var dataSet: List<Character> = listOf()) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterNameView: TextView = view.findViewById(R.id.characterName)
        val characterImageView: ImageView = view.findViewById(R.id.characterImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.characterNameView.text = dataSet[position].name
        val characterImage = dataSet[position].image
        if(characterImage != null) {
                holder.characterImageView.setImageResource(characterImage)
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<Character>) {
        dataSet = newList
    }
}
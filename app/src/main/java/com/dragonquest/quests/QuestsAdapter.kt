package com.dragonquest.quests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.models.Quest

class QuestsAdapter(private var dataSet: List<Quest> = listOf()) :
    RecyclerView.Adapter<QuestsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questLevelView: TextView = view.findViewById(R.id.questLevel)
        val questNameView: TextView = view.findViewById(R.id.questName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quest_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questLevelView.text = dataSet[position].level.toString()
        holder.questNameView.text = dataSet[position].name

    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<Quest>) {
        dataSet = newList
    }

}
package com.dragonquest.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.models.Quest
import com.dragonquest.models.UserQuest

class QuestsAdapter(val navController : NavController, private var dataSet: List<UserQuest> = listOf()) :
    RecyclerView.Adapter<QuestsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thisView = view
        val questLevelView: TextView = view.findViewById(R.id.questLevel)
        val questNameView: TextView = view.findViewById(R.id.questName)
        val questTimeView: TextView = view.findViewById(R.id.questTime)
        val questExperienceView: TextView = view.findViewById(R.id.questExperience)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quest_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (id, name, level, experience, time, _) = dataSet[position].quest

        holder.questLevelView.text = level.toString()
        holder.questNameView.text = name
        holder.questTimeView.text = "($time hr)"
        holder.questExperienceView.text = "$experience xp"

        holder.thisView.setOnClickListener { it: View ->
            val bundle = Bundle()
            bundle.putInt("questId", id)
            navController.navigate(R.id.action_questsFragment_to_questDetailsFragment, bundle)
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<UserQuest>) {
        dataSet = newList
    }

}
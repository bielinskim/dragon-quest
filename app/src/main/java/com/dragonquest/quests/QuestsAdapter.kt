package com.dragonquest.quests

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.MainActivity
import com.dragonquest.R
import com.dragonquest.models.Quest
import com.dragonquest.questdetails.QuestDetailsActivity
import com.dragonquest.utils.getLevel

class QuestsAdapter(private val activity: FragmentActivity?, private var dataSet: List<Quest> = listOf()) :
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
        val (id, name, level, experience, time, _) = dataSet[position]

        holder.questLevelView.text = level.toString()
        holder.questNameView.text = name
        holder.questTimeView.text = "($time hr)"
        holder.questExperienceView.text = "$experience xp"

        holder.thisView.setOnClickListener { it: View ->
            val intent = Intent(activity, QuestDetailsActivity::class.java).apply {
                Log.d("D1", id.toString())
                putExtra("QUEST_ID", id)
            }

            activity?.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size

    fun setData(newList : List<Quest>) {
        dataSet = newList
    }

}
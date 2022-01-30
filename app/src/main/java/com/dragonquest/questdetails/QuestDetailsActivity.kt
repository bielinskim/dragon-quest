package com.dragonquest.questdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.characters.CharactersAdapter
import com.dragonquest.characters.CharactersViewModel
import com.google.android.material.tabs.TabLayout
import com.dragonquest.models.Character
import com.dragonquest.quests.QuestsViewModel

class QuestDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_details)

        createQuestDetails()
        createCharactersList()

        val arrowBack : ImageView = findViewById(R.id.arrowBack)
        arrowBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun createQuestDetails() {
        val questTimeView = findViewById<TextView>(R.id.questDetailsTime)
        val questTimeDesc = findViewById<TextView>(R.id.questDetailsDescription)

        val questVM: QuestsViewModel by viewModels()
        val questId = intent.getIntExtra("QUEST_ID", 0)
        val quest = questVM.getUserById(questId)

        Log.d("D2", questVM.quests.value?.size.toString())

        val (id, name, level, experience, time, description) = quest

        questTimeView.text = time.toString()
        questTimeDesc.text = description

    }

    fun createCharactersList() {
        val characterVM: CharactersViewModel by viewModels()
        var characters : List<Character>? = characterVM.characters.value
        if(characters == null) {
            characters  = listOf()
        }

        val questDetailsCharactersRV: RecyclerView = findViewById(R.id.questDetailsCharactersRecyclerView)
        questDetailsCharactersRV.layoutManager =  LinearLayoutManager(this);
        val questDetailsCharactersAdapter = QuestDetailsCharactersAdapter(characters)
        questDetailsCharactersRV.adapter = questDetailsCharactersAdapter

        characterVM.characters.observe(this) { data ->
            questDetailsCharactersAdapter.setData(data)
            questDetailsCharactersAdapter.notifyDataSetChanged()
        }
    }
}
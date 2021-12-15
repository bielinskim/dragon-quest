package com.dragonquest.questdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.characters.CharactersAdapter
import com.dragonquest.characters.CharactersViewModel
import com.google.android.material.tabs.TabLayout

class QuestDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_details)

        val vm: CharactersViewModel by viewModels()
//        var characters : List<Character> = vm.characters.value
//        if(characters == null) {
//            characters  = listOf()
//        }


        val questDetailsRecyclerView: RecyclerView = findViewById(R.id.questDetailsRecyclerView)
        questDetailsRecyclerView.layoutManager =  LinearLayoutManager(this);
        val questDetailsAdapter = QuestDetailsAdapter(vm.characters.value)
        questDetailsRecyclerView.adapter = questDetailsAdapter





        vm.characters.observe(this, { data ->
            questDetailsAdapter.setData(data)
            questDetailsAdapter.notifyDataSetChanged()
        })

        val arrowBack : ImageView = findViewById(R.id.arrowBack)
        arrowBack.setOnClickListener {
            onBackPressed()
        }

    }
}
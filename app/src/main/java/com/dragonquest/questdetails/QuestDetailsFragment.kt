package com.dragonquest.questdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.viewmodels.CharactersViewModel
import com.dragonquest.models.Quest
import com.dragonquest.models.UserCharacter
import com.dragonquest.models.UserQuest
import com.dragonquest.viewmodels.QuestsViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class QuestDetailsFragment : Fragment() {

    private val questVM: QuestsViewModel by activityViewModels()
    private val chVM: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quest_details, container, false)
        val navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)

        var questId = arguments?.getInt("questId")
        if(questId == null) {
            questId = 0
        }
        val userQuest = questVM.getQuestById(questId)

        createQuestDetails(view, userQuest)
        val characterSlots = CharacterSlots(view, userQuest)
        createCharactersList(view, characterSlots)

        val arrowBack : ImageView = view.findViewById(R.id.arrowBack)
        arrowBack.setOnClickListener {
            navController.navigate(R.id.action_questDetailsFragment_to_questsFragment)
        }

        return view

    }

    fun createQuestDetails(view :View, userQuest : UserQuest) {
        val questLevelView = view.findViewById<TextView>(R.id.questDetailsLevel)
        val questTimeView = view.findViewById<TextView>(R.id.questDetailsTime)
        val questExperienceView = view.findViewById<TextView>(R.id.questDetailsExperience)
        val questNameView = view.findViewById<TextView>(R.id.questDetailsName)
        val questDescView = view.findViewById<TextView>(R.id.questDetailsDescription)


        val (_, name, level, experience, time, _, description) = userQuest.quest

        questLevelView.text = "Suggested level: $level"
        questTimeView.text = "Time: $time hr"

        questExperienceView.text = "Reward: $experience exp"

        questNameView.text = name
        questDescView.text = description

        val startQuestButton = view.findViewById<Button>(R.id.startQuestButton)

        startQuestButton.setOnClickListener {

        }

    }

    fun createCharactersList(view :View, characterSlots : CharacterSlots) {
        var userCharacters : List<UserCharacter>? = chVM.characters.value
        if(userCharacters == null) {
            userCharacters  = listOf()
        }

        val questDetailsCharactersRV: RecyclerView = view.findViewById(R.id.questDetailsCharactersRecyclerView)
        questDetailsCharactersRV.layoutManager =  LinearLayoutManager(context);
        val questDetailsCharactersAdapter = QuestDetailsCharactersAdapter(
            userCharacters,
            characterSlots
        )
        questDetailsCharactersRV.adapter = questDetailsCharactersAdapter

        chVM.characters.observe(viewLifecycleOwner) { data ->
            questDetailsCharactersAdapter.setData(data)
            questDetailsCharactersAdapter.notifyDataSetChanged()
        }
    }
}
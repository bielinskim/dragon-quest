package com.dragonquest.questdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.characters.CharactersViewModel
import com.dragonquest.models.Character
import com.dragonquest.models.Quest
import com.dragonquest.quests.QuestsViewModel

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
        val quest = questVM.getUserById(questId)

        createQuestDetails(view, quest)
        val characterSlots = CharacterSlots(view, quest)
        createCharactersList(view, quest, characterSlots)

        val arrowBack : ImageView = view.findViewById(R.id.arrowBack)
        arrowBack.setOnClickListener {
            navController.navigate(R.id.action_questDetailsFragment_to_questsFragment)
        }

        return view

    }

    fun createQuestDetails(view :View, quest : Quest) {
        val questLevelView = view.findViewById<TextView>(R.id.questDetailsLevel)
        val questTimeView = view.findViewById<TextView>(R.id.questDetailsTime)
        val questExperienceView = view.findViewById<TextView>(R.id.questDetailsExperience)
        val questNameView = view.findViewById<TextView>(R.id.questDetailsName)
        val questDescView = view.findViewById<TextView>(R.id.questDetailsDescription)


        val (_, name, level, experience, time, _, description) = quest

        questLevelView.text = "Suggested level: $level"
        questTimeView.text = "Time: $time hr"

        questExperienceView.text = "Reward: $experience exp"

        questNameView.text = name
        questDescView.text = description

    }

    fun createCharactersList(view :View, quest : Quest, characterSlots : CharacterSlots) {
        var characters : List<Character>? = chVM.characters.value
        if(characters == null) {
            characters  = listOf()
        }

        val questDetailsCharactersRV: RecyclerView = view.findViewById(R.id.questDetailsCharactersRecyclerView)
        questDetailsCharactersRV.layoutManager =  LinearLayoutManager(context);
        val questDetailsCharactersAdapter = QuestDetailsCharactersAdapter(
            characters,
            characterSlots
        )
        questDetailsCharactersRV.adapter = questDetailsCharactersAdapter

        chVM.characters.observe(viewLifecycleOwner) { data ->
            questDetailsCharactersAdapter.setData(data)
            questDetailsCharactersAdapter.notifyDataSetChanged()
        }
    }
}
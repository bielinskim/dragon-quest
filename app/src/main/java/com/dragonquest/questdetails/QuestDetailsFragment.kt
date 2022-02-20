package com.dragonquest.questdetails

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.viewmodels.CharactersViewModel
import com.dragonquest.models.Quest
import com.dragonquest.models.StartQuest
import com.dragonquest.models.UserCharacter
import com.dragonquest.models.UserQuest
import com.dragonquest.utils.RetrofitService
import com.dragonquest.viewmodels.QuestsViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class QuestDetailsFragment : Fragment() {

    private val questVM: QuestsViewModel by activityViewModels()
    private val chVM: CharactersViewModel by activityViewModels()
    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quest_details, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)

        var questId = arguments?.getInt("questId")
        if(questId == null) {
            questId = 0
        }
        val userQuest = questVM.getQuestById(questId)
        val characterSlots = CharacterSlots(view, userQuest)

        createQuestDetails(view, userQuest, characterSlots)
        createCharactersList(view, characterSlots)

        val arrowBack : ImageView = view.findViewById(R.id.arrowBack)
        arrowBack.setOnClickListener {
            navController.navigate(R.id.action_questDetailsFragment_to_questsFragment)
        }

        return view

    }

    fun createQuestDetails(view :View, userQuest : UserQuest, characterSlots :CharacterSlots) {
        val questLevelView = view.findViewById<TextView>(R.id.questDetailsLevel)
        val questTimeView = view.findViewById<TextView>(R.id.questDetailsTime)
        val questExperienceView = view.findViewById<TextView>(R.id.questDetailsExperience)
        val questNameView = view.findViewById<TextView>(R.id.questDetailsName)
        val questDescView = view.findViewById<TextView>(R.id.questDetailsDescription)
        val questDetailsSuccessChance = view.findViewById<TextView>(R.id.questDetailsSuccessChance)
        val startQuestButton = view.findViewById<Button>(R.id.startQuestButton)


        val userQuestId = userQuest.userQuestId
        val (_, name, level, experience, time, _, description) = userQuest.quest

        questLevelView.text = "Suggested level: $level"
        questTimeView.text = "Time: $time hr"

        questExperienceView.text = "Reward: $experience exp"

        questNameView.text = name
        questDescView.text = description

        questDetailsSuccessChance.text = "0% chance"
        questDetailsSuccessChance.setTextColor(Color.parseColor("#dc3545"))

        startQuestButton.setOnClickListener {
            val characterSlots = characterSlots.characterSlots
            startQuest(characterSlots, userQuestId)
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

    fun startQuest(characterSlots : ArrayList<CharacterSlot>, userQuestId : Int) {
        val isAnySelected = characterSlots.any { it.userCharacter != null }


        if(isAnySelected) {
            val api = RetrofitService

            val userCharacterIds = arrayListOf<Int>()

            for (characterSlot in characterSlots) {
                val userCharacterId = characterSlot.userCharacter?.userCharacterId
                if(userCharacterId != null) {
                    userCharacterIds.add(userCharacterId)
                }
            }

            val startQuest = StartQuest(userQuestId, userCharacterIds)


            api.startQuest(startQuest, {
                val userQuestId = it?.userQuestId
                if (userQuestId != null) {
                    onQuestStarted()
                } else {
                    Log.e("APP", "Error while the quest starting")
                }
            },
            {
                if (it is String) {
                    Log.e("APP", it)
                }
            })
        }
    }

    fun onQuestStarted() {
        chVM.initializeData()
        questVM.initializeData()
        navController.navigate(R.id.action_questDetailsFragment_to_questsFragment)
    }
}
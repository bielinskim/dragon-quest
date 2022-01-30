package com.dragonquest.quests

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.common.TabNavigation

class QuestsFragment : Fragment() {

    private val vm: QuestsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quests, container, false)
        val charactersButton: ImageView = view.findViewById(R.id.charactersButton)
        val navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)

        val questsRecyclerView: RecyclerView = view.findViewById(R.id.questsRecyclerView)
        questsRecyclerView.layoutManager =  LinearLayoutManager(activity);
        val questsAdapter = QuestsAdapter(activity)
        questsRecyclerView.adapter = questsAdapter

        vm.quests.observe(viewLifecycleOwner) { data ->
            questsAdapter.setData(data)
            questsAdapter.notifyDataSetChanged()
        }

        charactersButton.setOnClickListener {
            navController.navigate(R.id.action_questsFragment_to_charactersFragment)
        }

        return view
    }
}
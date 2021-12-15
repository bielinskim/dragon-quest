package com.dragonquest.quests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.characters.CharactersAdapter
import com.dragonquest.characters.CharactersViewModel
import com.google.android.material.tabs.TabLayout

class QuestsFragment : Fragment() {

    private val vm: QuestsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quests, container, false)

        val questsRecyclerView: RecyclerView = view.findViewById(R.id.questsRecyclerView)
        questsRecyclerView.layoutManager =  LinearLayoutManager(activity);
        val questsAdapter = QuestsAdapter(activity)
        questsRecyclerView.adapter = questsAdapter

        vm.quests.observe(viewLifecycleOwner, { data ->
            questsAdapter.setData(data)
            questsAdapter.notifyDataSetChanged()
        })

        return view
    }
}
package com.dragonquest.characters

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
import com.google.android.material.tabs.TabLayout

class CharactersFragment : Fragment() {

    private val vm: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        val questsButton: ImageView = view.findViewById(R.id.questsButton)
        val navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)


        val charactersRecyclerView: RecyclerView = view.findViewById(R.id.charactersRecyclerView)
        charactersRecyclerView.layoutManager =  LinearLayoutManager(activity);
        val charactersAdapter = CharactersAdapter()
        charactersRecyclerView.adapter = charactersAdapter

        vm.characters.observe(viewLifecycleOwner) { data ->
            charactersAdapter.setData(data)
            charactersAdapter.notifyDataSetChanged()
        }

        questsButton.setOnClickListener {
            navController.navigate(R.id.action_charactersFragment_to_questsFragment)
        }

        return view;
    }

}
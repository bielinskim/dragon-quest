package com.dragonquest.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R

class CharactersFragment : Fragment() {

    private val vm: CharactersViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_characters, container, false)

        val charactersRecyclerView: RecyclerView = view.findViewById(R.id.charactersRecyclerView)
        charactersRecyclerView.layoutManager =  LinearLayoutManager(activity);
        val charactersAdapter = CharactersAdapter()
        charactersRecyclerView.adapter = charactersAdapter

        vm.characters.observe(viewLifecycleOwner, { data ->
            charactersAdapter.setData(data)
            charactersAdapter.notifyDataSetChanged()
        })

        return view;
    }
}
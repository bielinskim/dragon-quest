package com.dragonquest.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dragonquest.characters.CharactersFragment
import com.dragonquest.quests.QuestsFragment

private const val TABS_COUNT = 2

class GamePagerAdapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment)  {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CharactersFragment()
            1 -> QuestsFragment()
            else -> CharactersFragment()
        }
    }

    override fun getItemCount(): Int = TABS_COUNT
}
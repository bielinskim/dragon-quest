package com.dragonquest.common

import androidx.viewpager2.widget.ViewPager2
import com.dragonquest.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GameTabLayout {
    companion object {
        fun set(tabLayout : TabLayout, viewPager : ViewPager2) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> tab.setIcon(R.drawable.list)
                    1 ->tab.setIcon(R.drawable.swords)
                }
            }.attach()
        }
    }
}
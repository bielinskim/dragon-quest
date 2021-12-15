package com.dragonquest.common

import android.opengl.Visibility
import androidx.navigation.NavController
import com.dragonquest.R
import com.google.android.material.tabs.TabLayout

object TabNavigation {

    fun setTabNavigation(tabLayout : TabLayout, navController: NavController) {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.list))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.swords))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                if(tab.position == 1) {
                    navController.navigate(R.id.action_charactersFragment_to_questsFragment)
                }
                if(tab.position == 0) {
                    navController.navigate(R.id.action_questsFragment_to_charactersFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fun setVisibility() {

        }
    }
}
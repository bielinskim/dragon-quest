package com.dragonquest.common

import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dragonquest.R
import com.google.android.material.tabs.TabLayout

object TabNavigation {

    fun setTabNavigation(
        view: View,
        activity: FragmentActivity,
        tabSelectedHandler: (navController : NavController, position : Int) -> Unit
    ) {
        val tabLayout : TabLayout = view.findViewById(R.id.gameTabLayout)
        val navController = Navigation.findNavController(activity, R.id.navigationHost)

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.list))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.swords))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                tabSelectedHandler(navController, tab.position);

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                tabSelectedHandler(navController, tab.position);
            }
        })

        fun setVisibility() {

        }
    }
}
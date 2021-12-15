package com.dragonquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dragonquest.characters.CharactersViewModel
import com.dragonquest.common.TabNavigation
import com.dragonquest.quests.QuestsViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeData()
        initializeTabNavigation()
    }

    private fun initializeTabNavigation() {
        val tabLayout : TabLayout = findViewById(R.id.gameTabLayout)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        TabNavigation.setTabNavigation(tabLayout, navController)
    }

    private fun initializeData() {
        val cvm: CharactersViewModel by viewModels()
        val qvm: QuestsViewModel by viewModels()
        cvm.initializeData()
        qvm.initializeData()
    }
}
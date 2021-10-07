package com.dragonquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.dragonquest.characters.CharactersViewModel
import com.dragonquest.common.GamePagerAdapter
import com.dragonquest.common.GameTabLayout
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeData()
        setView()
    }

    private fun initializeData() {
        val vm: CharactersViewModel by viewModels()
        vm.initializeData()
    }

    private fun setView() {
        val viewPager : ViewPager2 = findViewById(R.id.gamePager)
        val tabLayout : TabLayout = findViewById(R.id.gameTabLayout);

        val gamePagerAdapter = GamePagerAdapter(this)
        viewPager.adapter = gamePagerAdapter

        GameTabLayout.set(tabLayout, viewPager)
    }
}
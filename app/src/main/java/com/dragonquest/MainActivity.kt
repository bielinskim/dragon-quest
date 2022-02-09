package com.dragonquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dragonquest.viewmodels.CharactersViewModel
import com.dragonquest.viewmodels.QuestsViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeData()
    }

    private fun initializeData() {
        val cvm: CharactersViewModel by viewModels()
        val qvm: QuestsViewModel by viewModels()
        cvm.initializeData()
        qvm.initializeData()
    }
}
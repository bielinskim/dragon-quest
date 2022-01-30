package com.dragonquest.utils

import android.util.Log


fun GetLevelProgress(experience: Int, minExp: Int, maxExp: Int): Int {

    val reqExp = (maxExp - minExp).toDouble()
    val currExp = (experience - minExp).toDouble()

    return (currExp / reqExp * 100).toInt()
}



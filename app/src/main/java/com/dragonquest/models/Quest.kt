package com.dragonquest.models

data class Quest(
    val questId: Int = 0,
    val name: String = "",
    val level: Int = 0,
    val experience: Int = 0,
    val time: Int = 0,
    val slots: Int = 0,
    val description: String = "",
)
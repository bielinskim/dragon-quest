package com.dragonquest.models

data class Quest(
    val id: Int = 0,
    val name: String = "",
    val level: Int = 0,
    val experience: Int = 0,
    val time: Int = 0,
    val description: String = "",
)
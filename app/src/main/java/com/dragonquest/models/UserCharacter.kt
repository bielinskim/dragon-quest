package com.dragonquest.models

data class UserCharacter(
    val userCharacterId: Int,
    val experience: Int,
    val character : Character,
    val usersQuests: ArrayList<UserQuest>,
)
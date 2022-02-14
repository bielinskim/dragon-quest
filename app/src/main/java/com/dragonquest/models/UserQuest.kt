package com.dragonquest.models

data class UserQuest(
    val userQuestId: Int = 0,
    val quest : Quest = Quest(),
)
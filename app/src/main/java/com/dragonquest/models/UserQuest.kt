package com.dragonquest.models

data class UserQuest(
    val userQuestId: Int = 0,
    val status : String = "",
    val startDate : String? = null,
    val quest : Quest = Quest(),
)
package com.dragonquest.models

import java.time.LocalDateTime

data class UserQuest(
    val userQuestId: Int = 0,
    val status : String = "",
    val startDate : String? = null,
    val quest : Quest = Quest(),
)
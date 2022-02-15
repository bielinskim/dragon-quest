package com.dragonquest.models

import java.util.*

data class User (
    val userId: Int?,
    val login: String,
    val password: String,
    val token: String?,
    val lastQuestDraw: Date?
)
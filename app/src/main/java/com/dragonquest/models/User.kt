package com.dragonquest.models

data class User (
    val userId: Int?,
    val login: String,
    val password: String,
    val token: String?,
)
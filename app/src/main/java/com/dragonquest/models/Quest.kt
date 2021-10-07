package com.dragonquest.models

data class Quest(
    val id: Long,
    val name: String,
    val level: Int,
    val description: String,
)
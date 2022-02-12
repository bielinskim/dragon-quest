package com.dragonquest.models

data class Character (
    val characterId: Int,
    val name: String,
    var experience: Int,
    val imageId: Int,
)
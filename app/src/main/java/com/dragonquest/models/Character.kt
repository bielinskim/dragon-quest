package com.dragonquest.models

data class Character (
    val id: Int,
    val name: String,
    var experience: Int,
    val imageId: Int,
)
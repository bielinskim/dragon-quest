package com.dragonquest.models

import androidx.annotation.DrawableRes

data class Character (
    val id: Int,
    val name: String,
    var experience: Int,
    @DrawableRes
    val image: Int?,
)
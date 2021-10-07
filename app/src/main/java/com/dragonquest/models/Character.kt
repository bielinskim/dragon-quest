package com.dragonquest.models

import androidx.annotation.DrawableRes

data class Character (
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
)
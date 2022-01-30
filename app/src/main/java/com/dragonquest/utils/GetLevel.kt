package com.dragonquest.utils

import com.dragonquest.config.LevelsConfig
import com.dragonquest.models.Level


fun getLevel(experience: Int): Level {

    var level = LevelsConfig.levels.firstNotNullOfOrNull { it.takeIf { it.minExp <= experience && it.maxExp >= experience } }

    if(level === null) {
        level = LevelsConfig.defaultLevel
    }

    return level
}
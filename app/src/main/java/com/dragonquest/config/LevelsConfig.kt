package com.dragonquest.config

import com.dragonquest.models.Level

object LevelsConfig {
    // nextLevel = level => 500 * (level * level) - (500 * level)
    val levels = listOf(
        Level(1, 1, 0, 999),
        Level(2, 2, 1000, 2999),
        Level(3, 3, 3000, 5999),
        Level(4, 4, 6000, 9999),
        Level(5, 5, 10000, 14999),
        Level(6, 6, 15000, 20999),
        Level(7, 7, 21000, 27999),
        Level(8, 8, 28000, 35999),
        Level(9, 9, 36000, 44999),
        Level(10, 10, 45000, 54999),
    )
    val defaultLevel = Level(0, 0, 0, 0)
}


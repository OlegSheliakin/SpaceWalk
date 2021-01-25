package com.spacexwalk.data.database.models

import androidx.room.PrimaryKey

/**
 * Created by olegsheliakin on 25.01.2021.
 */
data class Rocket(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String
)

package com.spacexwalk.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by olegsheliakin on 25.01.2021.
 */
@Entity
data class Rocket(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String
)

package com.spacexwalk.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spacexwalk.domain.entities.Links
import java.util.*

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@Entity
data class Launch(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date_utc")
    val dateUtc: Date,
    @ColumnInfo(name = "rocket_id")
    val rocketId: String,
    @ColumnInfo(name = "success")
    val success: Boolean?,
    @Embedded
    val links: Links?,
    @ColumnInfo(name = "details")
    val details: String?,
    @ColumnInfo(name = "upcoming")
    val isUpcoming: Boolean?,
    @ColumnInfo(name = "static_fire_date_utc")
    val staticFireDateUtc: Date?
)

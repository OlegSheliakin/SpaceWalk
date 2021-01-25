package com.spacexwalk.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@Entity(tableName = "company_info")
data class CompanyInfo(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "founder_name")
    val founderName: String,
    @ColumnInfo(name = "founded_year")
    val foundedYear: Int,
    val employees: Int,
    val vehicles: Int,
    @ColumnInfo(name = "launch_sites")
    val launchSites: Int,
    val valuation: Long
)

package com.spacexwalk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spacexwalk.data.database.daos.CompanyInfoDao
import com.spacexwalk.data.database.daos.LaunchesDao
import com.spacexwalk.data.database.daos.RocketsDao
import com.spacexwalk.data.database.models.CompanyInfo
import com.spacexwalk.data.database.models.Launch
import com.spacexwalk.data.database.models.Rocket

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Database(
    entities = [
        Launch::class,
        CompanyInfo::class,
        Rocket::class,
    ],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun companyInfoDao(): CompanyInfoDao
    abstract fun launchesDao(): LaunchesDao
    abstract fun rocketsDao(): RocketsDao
}

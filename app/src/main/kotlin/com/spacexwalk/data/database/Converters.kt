package com.spacexwalk.data.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Created by olegsheliakin on 26.01.2021.
 */
class Converters {

    @TypeConverter
    fun fromValue(value: ZonedDateTime): Long =
        value.toInstant().toEpochMilli()

    @TypeConverter
    fun toValue(value: Long): ZonedDateTime =
        Instant.ofEpochMilli(value).atZone(ZoneId.of("UTC"))
}

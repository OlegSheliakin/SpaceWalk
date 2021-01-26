package com.spacexwalk.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by olegsheliakin on 25.01.2021.
 */
object LocalTimeAdapter {

    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @FromJson
    fun fromJson(value: String): LocalDateTime =
        ZonedDateTime.parse(value, formatter)
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()

    @ToJson
    fun toJson(value: LocalDateTime): String =
        ZonedDateTime.of(value, ZoneId.systemDefault())
            .withZoneSameInstant(ZoneId.of("UTC"))
            .format(formatter)
}

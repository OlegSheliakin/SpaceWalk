package com.spacexwalk.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by olegsheliakin on 25.01.2021.
 */
object ZonedTimeAdapter {

    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @FromJson
    fun fromJson(value: String): ZonedDateTime =
        ZonedDateTime.parse(value, formatter)

    @ToJson
    fun toJson(value: ZonedDateTime): String =
        value.format(formatter)
}

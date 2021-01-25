package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@JsonClass(generateAdapter = true)
data class Launch(
    @Json(name = "flight_number") val flightNumber: Int,
    @Json(name = "name") val missionName: String,
    @Json(name = "date_utc") val launchDate: Date,
    @Json(name = "date_precision") val datePrecision: String?,
    @Json(name = "rocket") val rocketId: String,
    @Json(name = "success") val launchSuccess: Boolean?,
    @Json(name = "links") val links: Links,
    @Json(name = "details") val details: String?,
    @Json(name = "upcoming") val upcoming: Boolean?,
    @Json(name = "static_fire_date_utc") val staticFireDate: Date?
)

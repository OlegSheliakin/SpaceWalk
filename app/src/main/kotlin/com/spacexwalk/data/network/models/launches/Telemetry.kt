package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Telemetry(
    @Json(name = "flight_club") val flightClub: String?
)

package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fairing(
    @Json(name = "reused") val reused: Boolean?,
    @Json(name = "recovery_attempt") val recoveryAttempt: Boolean?,
    @Json(name = "recovered") val recovered: Boolean?,
    @Json(name = "ship") val ship: String?
)

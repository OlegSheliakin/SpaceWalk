package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SecondStageSummary(
    @Json(name = "block") val block: Int?,
    @Json(name = "payloads") val payloads: List<Payload>
)

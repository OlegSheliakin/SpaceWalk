package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FirstStageSummary(@Json(name = "cores") val cores: List<CoreSummary>)

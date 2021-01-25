package com.spacexwalk.data.network.models.rockets

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by olegsheliakin on 25.01.2021.
 */
@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String
)

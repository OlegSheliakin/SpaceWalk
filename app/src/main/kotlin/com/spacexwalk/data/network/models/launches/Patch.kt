package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@JsonClass(generateAdapter = true)
data class Patch(
    @Json(name = "small") val smallIcon: String,
    @Json(name = "large") val largeIcon: String
)

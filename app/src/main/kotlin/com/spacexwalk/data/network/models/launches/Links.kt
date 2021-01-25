package com.spacexwalk.data.network.models.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "patch") val patch: Patch?,
    @Json(name = "presskit") val pressKit: String?,
    @Json(name = "article") val article: String?,
    @Json(name = "wikipedia") val wikipedia: String?,
    @Json(name = "webcast") val webCast: String?,
    @Json(name = "youtube_id") val youtubeId: String?
)

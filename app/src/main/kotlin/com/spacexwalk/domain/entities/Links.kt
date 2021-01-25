package com.spacexwalk.domain.entities

import com.spacexwalk.data.network.models.launches.Patch

data class Links(
    val patch: Patch?,
    val pressKit: String?,
    val article: String?,
    val wikipedia: String?,
    val webCast: String?,
    val youtubeId: String?
)

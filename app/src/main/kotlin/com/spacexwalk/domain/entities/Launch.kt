package com.spacexwalk.domain.entities

import java.time.ZonedDateTime

/**
 * Created by olegsheliakin on 26.01.2021.
 */
data class Launch(
    val name: String,
    val dateUtc: ZonedDateTime,
    val rocketId: String,
    val success: Boolean?,
    val links: Links?,
    val details: String?,
    val isUpcoming: Boolean?,
    val staticFireDateUtc: ZonedDateTime?
)

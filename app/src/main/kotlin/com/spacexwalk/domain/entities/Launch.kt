package com.spacexwalk.domain.entities

import java.time.ZonedDateTime

/**
 * Created by olegsheliakin on 26.01.2021.
 */
data class Launch(
    val missionName: String,
    val launchDateUtc: ZonedDateTime,
    val rocketId: String,
    val launchSuccess: Boolean?,
    val links: Links?,
    val details: String?,
    val isUpcoming: Boolean?,
    val staticFireDateUtc: ZonedDateTime?
)

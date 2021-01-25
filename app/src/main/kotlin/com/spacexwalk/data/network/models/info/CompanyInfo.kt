package com.spacexwalk.data.network.models.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
@JsonClass(generateAdapter = true)
data class CompanyInfo(
    @Json(name = "name") val name: String,
    @Json(name = "founder") val founder: String,
    @Json(name = "founded") val foundedYear: Int,
    @Json(name = "employees") val employees: Int,
    @Json(name = "launch_sites") val launchSites: Int,
    @Json(name = "valuation") val valuation: Long
)

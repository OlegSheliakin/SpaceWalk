package com.spacexwalk.domain.entities

/**
 * Created by olegsheliakin on 27.01.2021.
 */
data class CompanyInfo(
    val name: String,
    val founderName: String,
    val foundedYear: Int,
    val employees: Int,
    val launchSites: Int,
    val valuation: Long
)

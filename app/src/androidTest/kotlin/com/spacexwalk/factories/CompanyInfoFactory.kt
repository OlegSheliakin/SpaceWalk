package com.spacexwalk.factories

import com.spacexwalk.data.database.models.CompanyInfo

/**
 * Factory for [CompanyInfo]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
object CompanyInfoFactory {

    fun companyInfo() = CompanyInfo(
        name = "SpaceX",
        founderName = "Elon Musk",
        foundedYear = 2002,
        employees = 8000,
        launchSites = 3,
        valuation = 52000000000
    )
}

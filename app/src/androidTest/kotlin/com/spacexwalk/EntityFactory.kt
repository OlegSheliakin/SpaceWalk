package com.spacexwalk

import com.spacexwalk.data.database.models.CompanyInfo

/**
 * Created by olegsheliakin on 26.01.2021.
 */
object EntityFactory {

    fun companyInfo() = CompanyInfo(
        name = "SpaceX",
        founderName = "Elon Musk",
        foundedYear = 2002,
        employees = 8000,
        launchSites = 3,
        valuation = 52000000000
    )
}

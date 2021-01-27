package com.spacexwalk.data.mappers

import com.spacexwalk.data.database.models.CompanyInfo as DbModel
import com.spacexwalk.data.network.models.info.CompanyInfo as NetworkModel
import com.spacexwalk.domain.entities.CompanyInfo as DomainModel

/**
 * Created by olegsheliakin on 27.01.2021.
 */
fun NetworkModel.toDbModel() = DbModel(
    name = name,
    founderName = founder,
    foundedYear = foundedYear,
    employees = employees,
    launchSites = launchSites,
    valuation = valuation
)

fun DbModel.toDomainModel() = DomainModel(
    name = name,
    founderName = founderName,
    foundedYear = foundedYear,
    employees = employees,
    launchSites = launchSites,
    valuation = valuation
)

package com.spacexwalk.data.mappers

import com.spacexwalk.data.database.models.Launch as DbModel
import com.spacexwalk.data.network.models.launches.Launch as NetworkModel
import com.spacexwalk.domain.entities.Launch as DomainModel

/**
 * Created by olegsheliakin on 27.01.2021.
 */
fun NetworkModel.toDbModel() = DbModel(
    missionName = missionName,
    launchDateUtc = launchDate,
    launchSuccess = launchSuccess,
    rocketId = rocketId,
    details = details,
    isUpcoming = upcoming,
    links = links.toDomainModel(),
    staticFireDateUtc = staticFireDate
)

fun List<NetworkModel>.toDbModels() = map { it.toDbModel() }

fun DbModel.toDomainModel() = DomainModel(
    missionName = missionName,
    launchDateUtc = launchDateUtc,
    launchSuccess = launchSuccess,
    rocketId = rocketId,
    details = details,
    isUpcoming = isUpcoming,
    links = links,
    staticFireDateUtc = staticFireDateUtc
)

fun List<DbModel>.toDomainModels() = map { it.toDomainModel() }

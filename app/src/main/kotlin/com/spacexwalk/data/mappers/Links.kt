package com.spacexwalk.data.mappers

import com.spacexwalk.data.network.models.launches.Links as NetworkModel
import com.spacexwalk.domain.entities.Links as DomainModel

/**
 * Created by olegsheliakin on 27.01.2021.
 */
fun NetworkModel.toDomainModel() = DomainModel(
    smallIcon = patch?.smallIcon,
    largeIcon = patch?.largeIcon,
    presskit = pressKit,
    article = article,
    wikipedia = wikipedia,
    webCast = webCast,
    youtubeId = youtubeId
)

package com.spacexwalk.domain.repositories

import com.spacexwalk.core.StreamProvider
import com.spacexwalk.domain.entities.Launch

/**
 * Created by Oleg Sheliakin on 26.01.2021.
 */
interface LaunchesRepository : StreamProvider<Unit, List<Launch>>

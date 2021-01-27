package com.spacexwalk.domain.repositories

import com.spacexwalk.domain.entities.Launch
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 26.01.2021.
 */
interface LaunchesRepository {
    fun stream(): Flowable<List<Launch>>
}

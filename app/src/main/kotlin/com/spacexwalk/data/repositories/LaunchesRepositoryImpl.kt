package com.spacexwalk.data.repositories

import com.spacexwalk.core.StreamRequest
import com.spacexwalk.core.StreamResult
import com.spacexwalk.domain.entities.Launch
import com.spacexwalk.domain.repositories.LaunchesRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 26.01.2021.
 */
class LaunchesRepositoryImpl @Inject constructor() : LaunchesRepository {

    override fun stream(request: StreamRequest<Unit>): Flowable<StreamResult<List<Launch>>> =
        Flowable.empty()
}

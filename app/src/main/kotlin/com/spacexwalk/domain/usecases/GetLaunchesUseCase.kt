package com.spacexwalk.domain.usecases

import com.spacexwalk.domain.entities.Launch
import com.spacexwalk.domain.repositories.LaunchesRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by olegsheliakin on 26.01.2021.
 */
class GetLaunchesUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {

    fun execute(): Flowable<List<Launch>> =
        launchesRepository.stream()
}

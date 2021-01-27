package com.spacexwalk.data.repositories

import com.spacexwalk.data.database.daos.LaunchesDao
import com.spacexwalk.data.mappers.toDbModels
import com.spacexwalk.data.mappers.toDomainModels
import com.spacexwalk.data.network.services.LaunchesService
import com.spacexwalk.domain.entities.Launch
import com.spacexwalk.domain.repositories.LaunchesRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 26.01.2021.
 */
class LaunchesRepositoryImpl @Inject constructor(
    private val launchesDao: LaunchesDao,
    private val launchesService: LaunchesService
) : LaunchesRepository {

    override fun stream(fresh: Boolean): Flowable<List<Launch>> =
        if (fresh) {
            getFreshStream()
        } else {
            Flowable
                .concat(
                    launchesDao.getAll().onErrorComplete().toFlowable(),
                    getFreshStream()
                )
        }.map { it.toDomainModels() }

    private fun getFreshStream() = launchesService.getAllLaunches()
        .map { it.toDbModels() }
        .doOnSuccess(launchesDao::replaceAll)
        .toFlowable()
        .concatWith(launchesDao.stream().skip(1))
}

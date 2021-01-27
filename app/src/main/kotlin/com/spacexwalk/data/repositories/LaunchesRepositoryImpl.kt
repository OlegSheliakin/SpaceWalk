package com.spacexwalk.data.repositories

import com.spacexwalk.data.database.daos.LaunchesDao
import com.spacexwalk.data.mappers.toDbModels
import com.spacexwalk.data.mappers.toDomainModels
import com.spacexwalk.data.network.services.LaunchesService
import com.spacexwalk.domain.entities.Launch
import com.spacexwalk.domain.repositories.LaunchesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 26.01.2021.
 */
class LaunchesRepositoryImpl @Inject constructor(
    private val launchesDao: LaunchesDao,
    private val launchesService: LaunchesService
) : LaunchesRepository {

    override fun stream(): Flowable<List<Launch>> =
        launchesDao.getAll()
            .onErrorComplete()
            .toFlowable()
            .concatWith(launchesDao.stream().skip(1))
            .map { it.toDomainModels() }

    override fun refresh(): Completable =
        launchesService.getAllLaunches()
            .map { it.toDbModels() }
            .doOnSuccess(launchesDao::replaceAll)
            .ignoreElement()
}

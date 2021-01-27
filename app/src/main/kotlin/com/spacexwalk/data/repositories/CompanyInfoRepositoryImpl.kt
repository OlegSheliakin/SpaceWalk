package com.spacexwalk.data.repositories

import com.spacexwalk.data.database.daos.CompanyInfoDao
import com.spacexwalk.data.mappers.toDbModel
import com.spacexwalk.data.mappers.toDomainModel
import com.spacexwalk.data.network.services.CompanyInfoService
import com.spacexwalk.domain.entities.CompanyInfo
import com.spacexwalk.domain.repositories.CompanyInfoRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by olegsheliakin on 27.01.2021.
 */
class CompanyInfoRepositoryImpl @Inject constructor(
    private val companyInfoDao: CompanyInfoDao,
    private val companyInfoService: CompanyInfoService
) : CompanyInfoRepository {

    override fun stream(): Flowable<CompanyInfo> =
        Flowable
            .concat(
                companyInfoDao.get().onErrorComplete().toFlowable(),
                companyInfoService.getInfo()
                    .map { it.toDbModel() }
                    .doOnSuccess(companyInfoDao::replace)
                    .toFlowable()
                    .concatWith(companyInfoDao.stream().skip(1))
            ).map { it.toDomainModel() }
}

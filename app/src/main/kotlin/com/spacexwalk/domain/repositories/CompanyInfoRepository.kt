package com.spacexwalk.domain.repositories

import com.spacexwalk.domain.entities.CompanyInfo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by olegsheliakin on 27.01.2021.
 */
interface CompanyInfoRepository {
    fun stream(): Flowable<CompanyInfo>
    fun refresh(): Completable
}

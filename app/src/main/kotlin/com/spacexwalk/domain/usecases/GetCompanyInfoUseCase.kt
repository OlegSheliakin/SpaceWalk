package com.spacexwalk.domain.usecases

import com.spacexwalk.domain.entities.CompanyInfo
import com.spacexwalk.domain.repositories.CompanyInfoRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by olegsheliakin on 26.01.2021.
 */
class GetCompanyInfoUseCase @Inject constructor(
    private val companyInfoRepository: CompanyInfoRepository
) {

    fun execute(): Flowable<CompanyInfo> =
        companyInfoRepository.stream()
}

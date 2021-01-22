package com.spacexwalk.data.network.services

import com.spacexwalk.data.network.models.info.CompanyInfo
import io.reactivex.Single
import retrofit2.http.GET

interface CompanyInfoService {

    @GET("info")
    fun getInfo(): Single<CompanyInfo>
}
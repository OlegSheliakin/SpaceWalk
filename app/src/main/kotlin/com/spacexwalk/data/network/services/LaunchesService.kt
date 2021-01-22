package com.spacexwalk.data.network.services

import com.spacexwalk.data.network.models.launches.Launch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesService {

    @GET("launches")
    fun getAllLaunches(
        @Query("flight_id") flightId: String? = null,
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("land_success") landSucess: Boolean? = null,
        @Query("site_id") siteId: String? = null,
        @Query("customer") customer: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("launch_success") launchSuccess: Boolean? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): Single<List<Launch>>
}

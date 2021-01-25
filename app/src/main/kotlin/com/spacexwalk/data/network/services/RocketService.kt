package com.spacexwalk.data.network.services

import com.spacexwalk.data.network.models.rockets.Rocket
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by olegsheliakin on 25.01.2021.
 */
interface RocketService {
    @GET("rockets/{id}")
    fun getRocket(@Path("id") id: String): Single<Rocket>
}

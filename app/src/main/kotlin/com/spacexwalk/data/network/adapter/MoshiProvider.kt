package com.spacexwalk.data.network.adapter

import com.squareup.moshi.Moshi

/**
 * Created by Oleg Sheliakin on 22.01.2021.
 */
object MoshiProvider {
    val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(LocalTimeAdapter)
            .build()
    }
}

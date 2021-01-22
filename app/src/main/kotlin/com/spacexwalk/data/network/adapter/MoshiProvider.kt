package com.spacexwalk.data.network.adapter

import com.squareup.moshi.Moshi

object MoshiProvider {
    val moshi: Moshi by lazy {
        Moshi.Builder()
            .build()
    }
}

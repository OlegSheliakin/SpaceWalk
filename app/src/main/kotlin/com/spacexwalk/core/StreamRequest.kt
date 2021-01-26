package com.spacexwalk.core

/**
 * Created by olegsheliakin on 26.01.2021.
 */
data class StreamRequest<PARAMS>(
    val params: PARAMS,
    val shouldRefresh: Boolean = false
)

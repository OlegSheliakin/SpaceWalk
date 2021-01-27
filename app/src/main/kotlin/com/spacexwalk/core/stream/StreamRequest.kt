package com.spacexwalk.core.stream

/**
 * Created by olegsheliakin on 26.01.2021.
 */
sealed class StreamRequest<PARAMS> {

    abstract val params: PARAMS

    data class All<PARAMS>(override val params: PARAMS) : StreamRequest<PARAMS>()

    data class Cached<PARAMS>(override val params: PARAMS) : StreamRequest<PARAMS>()

    data class Fresh<PARAMS>(override val params: PARAMS) : StreamRequest<PARAMS>()
}

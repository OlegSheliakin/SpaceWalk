package com.spacexwalk.core

import io.reactivex.Flowable

/**
 * Created by olegsheliakin on 26.01.2021.
 */
interface StreamProvider<PARAMS, DATA> {
    fun stream(request: StreamRequest<PARAMS>): Flowable<StreamResult<DATA>>
}

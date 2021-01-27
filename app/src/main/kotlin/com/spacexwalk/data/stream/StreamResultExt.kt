package com.spacexwalk.data.stream

import com.spacexwalk.core.stream.StreamResult
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by olegsheliakin on 27.01.2021.
 */
fun <T : Any> Flowable<T>.toStreamResult(source: StreamResult.Source): Flowable<StreamResult<T>> =
    map<StreamResult<T>> { StreamResult.Success(it, source) }
        .onErrorReturn { StreamResult.Error(it) }

fun <T : Any> Maybe<T>.toStreamResult(
    source: StreamResult.Source,
    wrapError: Boolean = true
): Flowable<StreamResult<T>> =
    if (wrapError) {
        toFlowable().map<StreamResult<T>> { StreamResult.Success(it, source) }
            .onErrorReturn { StreamResult.Success(null, source) }
    } else {
        toFlowable().toStreamResult(source)
    }

fun <T : Any> Single<T>.toStreamResult(source: StreamResult.Source): Flowable<StreamResult<T>> =
    toFlowable().toStreamResult(source)

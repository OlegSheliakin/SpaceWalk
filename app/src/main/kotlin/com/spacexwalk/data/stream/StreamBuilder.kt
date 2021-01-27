package com.spacexwalk.data.stream

import com.spacexwalk.core.StreamProvider
import com.spacexwalk.core.StreamRequest
import com.spacexwalk.core.StreamResult
import com.spacexwalk.core.StreamResult.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by olegsheliakin on 26.01.2021.
 */
fun <PARAMS : Any, DATA : Any> streamBuilder(
    fetcher: (PARAMS) -> Single<DATA>,
    cache: (PARAMS) -> Maybe<DATA>,
    persister: (DATA) -> Unit,
    streamer: (PARAMS) -> Flowable<DATA>,
): StreamProvider<PARAMS, DATA> = StreamProvider { streamRequest ->
    val params = streamRequest.params

    when (streamRequest) {
        is StreamRequest.All ->
            Flowable.concat(
                cache.invoke(params)
                    .toFlowable()
                    .map { Success(it, Source.CACHE) }
                    .onErrorReturn { Success(null, Source.CACHE) },
                createFreshStream(
                    params = params,
                    fetcher = fetcher,
                    persister = persister,
                    streamer = streamer
                )
            ).startWith(Loading)
        is StreamRequest.Cached -> createCachedStream(params = params, cache = cache, streamer = streamer)
        is StreamRequest.Fresh -> createFreshStream(
            params = params,
            fetcher = fetcher,
            persister = persister,
            streamer = streamer
        ).startWith(Loading)
    }.startWith(Initial)
}

private fun <PARAMS : Any, DATA : Any> createCachedStream(
    params: PARAMS,
    cache: (PARAMS) -> Maybe<DATA>,
    streamer: (PARAMS) -> Flowable<DATA>
): Flowable<StreamResult<DATA>> =
    cache.invoke(params)
        .toFlowable()
        .map<StreamResult<DATA>> { Success(it, Source.CACHE) }
        .onErrorReturn { Success(null, Source.CACHE) }
        .concatWith(listenStream(params, streamer))

private fun <PARAMS : Any, DATA : Any> createFreshStream(
    params: PARAMS,
    fetcher: (PARAMS) -> Single<DATA>,
    persister: (DATA) -> Unit,
    streamer: (PARAMS) -> Flowable<DATA>
): Flowable<StreamResult<DATA>> {
    val fetchStream = fetcher.invoke(params)
        .doOnSuccess(persister)
        .toStreamResult(Source.REMOTE)

    return fetchStream.concatWith(listenStream(params, streamer))
}

private fun <PARAMS : Any, DATA : Any> listenStream(
    params: PARAMS,
    streamer: (PARAMS) -> Flowable<DATA>
): Flowable<StreamResult<DATA>> =
    streamer.invoke(params).toStreamResult(Source.CACHE).skip(1)

package com.spacexwalk.data.repositories

import com.spacexwalk.core.StreamRequest
import com.spacexwalk.core.StreamResult.*
import com.spacexwalk.data.stream.streamBuilder
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor
import org.junit.jupiter.api.Test

/**
 * Test for stream
 *
 * Created by olegsheliakin on 26.01.2021.
 */
class StreamTest {

    private val fakeDao = FakeDao()

    @Test
    fun streamAll() {
        val streamProvider = streamBuilder<Unit, List<String>>(
            fetcher = { Single.error(EXCEPTION) },
            cache = { Maybe.error(Exception()) },
            persister = { fakeDao.save(it) },
            streamer = { fakeDao.stream() }
        )

        val testObserver = streamProvider.stream(StreamRequest.All(Unit)).test()

        fakeDao.save(CACHED_RESPONSE)

        testObserver
            .assertValues(
                Initial,
                Loading,
                Success(null, Source.CACHE),
                Error(EXCEPTION),
                Success(CACHED_RESPONSE, Source.CACHE),
            )
    }

    @Test
    fun streamAllNetworkError() {
        val streamProvider = streamBuilder<Unit, List<String>>(
            fetcher = { Single.just(NETWORK_RESPONSE) },
            cache = { Maybe.error(Exception()) },
            persister = { fakeDao.save(it) },
            streamer = { fakeDao.stream() }
        )

        val testObserver = streamProvider.stream(StreamRequest.All(Unit)).test()

        fakeDao.save(CACHED_RESPONSE)

        testObserver
            .assertValues(
                Initial,
                Loading,
                Success(null, Source.CACHE),
                Success(NETWORK_RESPONSE, Source.REMOTE),
                Success(CACHED_RESPONSE, Source.CACHE),
            )
    }

    @Test
    fun streamCached() {
        val streamProvider = streamBuilder<Unit, List<String>>(
            fetcher = { Single.just(NETWORK_RESPONSE) },
            cache = { Maybe.just(CACHED_RESPONSE) },
            persister = { fakeDao.save(it) },
            streamer = { fakeDao.stream() }

        )
        val testObserver = streamProvider.stream(StreamRequest.Cached(Unit)).test()

        fakeDao.save(listOf("new"))

        testObserver
            .assertValues(
                Initial,
                Success(CACHED_RESPONSE, Source.CACHE),
                Success(listOf("new"), Source.CACHE),
            )
    }

    @Test
    fun streamFresh() {
        val streamProvider = streamBuilder<Unit, List<String>>(
            fetcher = { Single.just(NETWORK_RESPONSE) },
            cache = { Maybe.just(CACHED_RESPONSE) },
            persister = { fakeDao.save(it) },
            streamer = { fakeDao.stream() }
        )

        val testObserver = streamProvider.stream(StreamRequest.Fresh(Unit)).test()

        fakeDao.save(CACHED_RESPONSE)

        testObserver
            .assertValues(
                Initial,
                Loading,
                Success(NETWORK_RESPONSE, Source.REMOTE),
                Success(CACHED_RESPONSE, Source.CACHE),
            )
    }

    @Test
    fun streamFreshNetworkError() {
        val streamProvider = streamBuilder<Unit, List<String>>(
            fetcher = { Single.error(EXCEPTION) },
            cache = { Maybe.just(CACHED_RESPONSE) },
            persister = { fakeDao.save(it) },
            streamer = { fakeDao.stream() }
        )

        val testObserver = streamProvider.stream(StreamRequest.Fresh(Unit)).test()

        fakeDao.save(CACHED_RESPONSE)

        testObserver
            .assertValues(
                Initial,
                Loading,
                Error(EXCEPTION),
                Success(CACHED_RESPONSE, Source.CACHE),
            )
    }

    companion object {
        private val CACHED_RESPONSE = listOf("cached")
        private val NETWORK_RESPONSE = listOf("network")
        private val EXCEPTION = Exception()
    }

    private class FakeDao {

        private val _items = mutableListOf<String>()
        private val publishProcessor = BehaviorProcessor.createDefault<List<String>>(_items)

        fun save(items: List<String>) {
            _items.clear()
            _items.addAll(items)

            publishProcessor.onNext(_items)
        }

        fun stream(): Flowable<List<String>> = publishProcessor.hide()
    }
}

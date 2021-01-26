package com.spacexwalk.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Test fot [StreamResult]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
internal class StreamResultTest {

    @Test
    @DisplayName("When result is Initial must not be terminated")
    fun initialIsTerminated() {
        val streamResult = StreamResult.Initial

        assertFalse(streamResult.isTerminated())
    }

    @Test
    @DisplayName("When result is Loading, it must not be terminated")
    fun loadingIsTerminated() {
        val streamResult = StreamResult.Loading

        assertFalse(streamResult.isTerminated())
    }

    @Test
    @DisplayName("When result is Success and source is Source.CACHE, it must not be terminated")
    fun successCachedIsTerminated() {
        val streamResult = StreamResult.Success(data = Unit, source = StreamResult.Source.CACHE)

        assertFalse(streamResult.isTerminated())
    }

    @Test
    @DisplayName("When result is Success and source is Source.REMOTE, it must be terminated")
    fun successRemoteIsTerminated() {
        val streamResult = StreamResult.Success(data = Unit, source = StreamResult.Source.REMOTE)

        assertTrue(streamResult.isTerminated())
    }

    @Test
    @DisplayName("Should return data when result is Success")
    fun dataSuccess() {
        val streamResult = StreamResult.Success(data = Unit, source = StreamResult.Source.REMOTE)

        assertNotNull(streamResult.data())
    }

    @Test
    @DisplayName("Should return data when result is Error and data is not null")
    fun dataError() {
        val streamResult = StreamResult.Error(data = Unit, error = null)

        assertNotNull(streamResult.data())
    }

    @Test
    @DisplayName("Should not return data when result is Loading")
    fun dataLoading() {
        val streamResult = StreamResult.Loading

        assertNull(streamResult.data())
    }

    @Test
    @DisplayName("Should not return data when result is Initial")
    fun dataInitial() {
        val streamResult = StreamResult.Initial

        assertNull(streamResult.data())
    }
}

package com.spacexwalk.core

/**
 * This class represents the stream data results.
 *
 * Created by Oleg Sheliakin on 26.01.2021.
 */
sealed class StreamResult<out T> {

    data class Success<out T>(
        val data: T,
        val source: Source
    ) : StreamResult<T>()

    data class Error<out T, out E>(
        val data: T?,
        val error: E?
    ) : StreamResult<T>()

    object Loading : StreamResult<Nothing>()

    object Initial : StreamResult<Nothing>()

    /**
     * It checks if [StreamResult] is terminated.
     * [StreamResult] is terminated if it is [Success] or [Error]
     */
    fun isTerminated(): Boolean = when (this) {
        is Success -> source == Source.REMOTE
        is Error<*, *> -> true
        else -> false
    }

    fun data(): T? = when (this) {
        is Success -> this.data
        is Error<T, *> -> this.data
        else -> null
    }

    enum class Source {
        CACHE, REMOTE
    }
}

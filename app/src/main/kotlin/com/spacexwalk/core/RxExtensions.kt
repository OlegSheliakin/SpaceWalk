package com.spacexwalk.core

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by olegsheliakin on 27.01.2021.
 */
private const val THROTTLE_TIME_WINDOW = 500L

fun <T> Observable<T>.throttling(): Observable<T> =
    throttleFirst(THROTTLE_TIME_WINDOW, TimeUnit.MILLISECONDS)

fun <T> Flowable<T>.applySchedulers(): Flowable<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun Completable.applySchedulers(): Completable =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

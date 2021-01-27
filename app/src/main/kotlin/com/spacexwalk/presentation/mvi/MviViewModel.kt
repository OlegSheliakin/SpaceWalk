package com.spacexwalk.presentation.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by olegsheliakin on 27.01.2021.
 */
abstract class MviViewModel<S : Any, A : Any>(state: S? = null) : ViewModel() {

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected val actions: PublishRelay<A> = PublishRelay.create<A>()

    private val statePublisher: BehaviorRelay<S> = BehaviorRelay.create<S>()

    private val stateInternal = MutableLiveData<S>()

    private val tag by lazy { javaClass.simpleName }

    /**
     * Returns the current stateInternal. It is equal to the last value returned by the store's reducer.
     */
    val state: LiveData<S> = MediatorLiveData<S>().apply {
        addSource(stateInternal) { data ->
            Timber.tag(tag).d("New state: $data")
            value = data
            statePublisher.accept(data)
        }
    }

    init {
        state?.let { s -> stateInternal.value = s }
    }

    protected fun observeState(): Observable<S> =
        statePublisher.hide()

    protected fun newState(state: S) {
        stateInternal.value = state
    }

    protected fun reduceState(func: (state: S) -> S) {
        stateInternal.value?.let {
            stateInternal.value = func.invoke(it)
        }
    }

    /**
     * Dispatches an action. This is the only way to trigger a syncState change.
     */
    fun dispatch(action: A) {
        Timber.tag(tag).d("New action: $action")
        actions.accept(action)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

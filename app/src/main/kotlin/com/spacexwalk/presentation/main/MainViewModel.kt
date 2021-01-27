package com.spacexwalk.presentation.main

import com.spacexwalk.core.applySchedulers
import com.spacexwalk.core.throttling
import com.spacexwalk.domain.repositories.CompanyInfoRepository
import com.spacexwalk.domain.repositories.LaunchesRepository
import com.spacexwalk.presentation.mvi.MviViewModel
import dagger.assisted.AssistedInject
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by olegsheliakin on 27.01.2021.
 */
class MainViewModel @AssistedInject constructor(
    private val launchesRepository: LaunchesRepository,
    private val companyInfoRepository: CompanyInfoRepository
) : MviViewModel<MainState, Unit>(state = MainState()) {

    init {
        subscribeToLaunchesStream()

        subscribeToCompanyInfoStream()

        handleRefreshAction()
    }

    private fun handleRefreshAction() {
        actions
            .ofType<Action.Refresh>()
            .throttling()
            .switchMapCompletable {
                Completable
                    .mergeArray(
                        launchesRepository.refresh(),
                        companyInfoRepository.refresh()
                    )
                    .applySchedulers()
                    .doOnSubscribe { reduceState { it.copy(isLoading = true) } }
                    .doOnComplete { reduceState { it.copy(isLoading = false) } }
                    .onErrorComplete()
            }
            .subscribeBy(onError = Timber::e)
            .addTo(compositeDisposable)
    }

    private fun subscribeToCompanyInfoStream() {
        companyInfoRepository
            .stream()
            .applySchedulers()
            .subscribeBy(
                onNext = { companyInfo ->
                    reduceState { it.copy(companyInfo = companyInfo) }
                },
                onError = {
                }
            )
            .addTo(compositeDisposable)
    }

    private fun subscribeToLaunchesStream() {
        launchesRepository
            .stream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { launches ->
                    reduceState { it.copy(launches = launches) }
                },
                onError = {
                }
            )
            .addTo(compositeDisposable)
    }
}

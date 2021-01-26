package com.spacexwalk.domain.usecases

import com.spacexwalk.domain.entities.Launch
import io.reactivex.Observable

/**
 * Created by olegsheliakin on 26.01.2021.
 */
class GetLaunchesUseCase {

    fun execute(): Observable<List<Launch>> =
        Observable.empty()
}

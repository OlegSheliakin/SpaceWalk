package com.spacexwalk.data.network.di

import com.spacexwalk.data.network.services.CompanyInfoService
import com.spacexwalk.data.network.services.LaunchesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideLaunchesService(retrofit: Retrofit): LaunchesService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideInfoService(retrofit: Retrofit): CompanyInfoService =
        retrofit.create()
}

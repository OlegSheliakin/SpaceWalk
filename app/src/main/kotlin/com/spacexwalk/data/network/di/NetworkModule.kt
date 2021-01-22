package com.spacexwalk.data.network.di

import com.spacexwalk.BuildConfig
import com.spacexwalk.data.network.BASE_URL
import com.spacexwalk.data.network.adapter.MoshiProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val NETWORK_TIMEOUT = 30L

    @Singleton
    @Provides
    fun provideDefaultRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(MoshiProvider.moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttp())
            .baseUrl(BASE_URL)
            .build()

    private fun createOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }

                writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                callTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            }
            .build()
}

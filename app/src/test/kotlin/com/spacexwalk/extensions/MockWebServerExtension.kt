package com.spacexwalk.extensions

import android.util.Log
import com.spacexwalk.data.network.adapter.MoshiProvider
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

/**
 * Created by olegsheliakin on 25.01.2021.
 */
class MockWebServerExtension : BeforeEachCallback, AfterEachCallback {

    private lateinit var retrofit: Retrofit

    val server: MockWebServer = MockWebServer()

    override fun beforeEach(context: ExtensionContext?) {
        server.start()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(MoshiProvider.moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(server.url("/"))
            .build()
    }

    override fun afterEach(context: ExtensionContext?) {
        try {
            server.shutdown()
        } catch (e: IOException) {
            Log.w("MockWebServer shutdown failed", e)
        }
    }

    fun <T> getService(clazz: Class<T>): T = retrofit.create(clazz)
}

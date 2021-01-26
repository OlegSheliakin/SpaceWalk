package com.spacexwalk.api

import com.spacexwalk.data.network.models.rockets.Rocket
import com.spacexwalk.data.network.services.RocketService
import com.spacexwalk.extensions.MockWebServerExtension
import com.spacexwalk.utils.createResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Created by olegsheliakin on 25.01.2021.
 */
class RocketsTest {

    private val rocketService by lazy {
        mockWebServerExtension.getService(RocketService::class.java)
    }

    @Test
    fun test() {
        mockWebServerExtension.server.enqueue(createResponse("/rocket.json"))

        val actual = rocketService.getRocket("any").blockingGet()

        Assertions.assertEquals(expected, actual)
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mockWebServerExtension = MockWebServerExtension()

        private val expected = Rocket(
            name = "Falcon 1",
            type = "rocket"
        )
    }
}

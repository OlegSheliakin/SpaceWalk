package com.spacexwalk.api

import com.spacexwalk.data.network.models.launches.Launch
import com.spacexwalk.data.network.models.launches.Links
import com.spacexwalk.data.network.models.launches.Patch
import com.spacexwalk.data.network.services.LaunchesService
import com.spacexwalk.extensions.MockWebServerExtension
import com.spacexwalk.utils.createResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.time.LocalDateTime

/**
 * Created by olegsheliakin on 25.01.2021.
 */
class LaunchesTest {

    private val launchesService by lazy {
        mockWebServerExtension.getService(LaunchesService::class.java)
    }

    @Test
    fun test() {
        mockWebServerExtension.server.enqueue(createResponse("/launches.json"))

        val actual = launchesService.getAllLaunches().blockingGet()

        Assertions.assertEquals(expected, actual)
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mockWebServerExtension = MockWebServerExtension()

        private val expected = listOf(
            Launch(
                flightNumber = 1,
                missionName = "FalconSat",
                launchDate = LocalDateTime.parse("2006-03-25T01:30"),
                datePrecision = "hour",
                rocketId = "5e9d0d95eda69955f709d1eb",
                launchSuccess = false,
                details = "Engine failure at 33 seconds and loss of vehicle",
                upcoming = false,
                links = Links(
                    patch = Patch(
                        smallIcon = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
                        largeIcon = "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
                    ),
                    pressKit = null,
                    webCast = "https://www.youtube.com/watch?v=0a_00nJ_Y88",
                    youtubeId = "0a_00nJ_Y88",
                    article = "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html",
                    wikipedia = "https://en.wikipedia.org/wiki/DemoSat"
                ),
                staticFireDate = LocalDateTime.parse("2006-03-17T03:00")
            ),
            Launch(
                flightNumber = 2,
                missionName = "DemoSat",
                launchDate = LocalDateTime.parse("2007-03-21T04:10:00"),
                datePrecision = "hour",
                rocketId = "5e9d0d95eda69955f709d1eb",
                launchSuccess = false,
                details = "Successful first stage burn and transition to second stage",
                upcoming = false,
                links = Links(
                    patch = Patch(
                        smallIcon = "https://images2.imgbox.com/4f/e3/I0lkuJ2e_o.png",
                        largeIcon = "https://images2.imgbox.com/be/e7/iNqsqVYM_o.png"
                    ),
                    pressKit = null,
                    webCast = "https://www.youtube.com/watch?v=Lk4zQ2wP-Nc",
                    youtubeId = "Lk4zQ2wP-Nc",
                    article = "https://www.space.com/3590-spacex-falcon-1-rocket-fails-reach-orbit.html",
                    wikipedia = "https://en.wikipedia.org/wiki/DemoSat"
                ),
                staticFireDate = null
            )
        )
    }
}

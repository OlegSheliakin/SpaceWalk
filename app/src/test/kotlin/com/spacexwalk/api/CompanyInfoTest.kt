package com.spacexwalk.api

import com.spacexwalk.data.network.models.info.CompanyInfo
import com.spacexwalk.data.network.services.CompanyInfoService
import com.spacexwalk.extensions.MockWebServerExtension
import com.spacexwalk.utils.createResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Created by olegsheliakin on 25.01.2021.
 */
class CompanyInfoTest {

    private val companyInfoService by lazy {
        mockWebServerExtension.getService(CompanyInfoService::class.java)
    }

    @Test
    fun test() {
        mockWebServerExtension.server.enqueue(createResponse("/company_info.json"))

        val actual = companyInfoService.getInfo().blockingGet()

        Assertions.assertEquals(expected, actual)
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mockWebServerExtension = MockWebServerExtension()

        private val expected = CompanyInfo(
            name = "SpaceX",
            founder = "Elon Musk",
            foundedYear = 2002,
            employees = 8000,
            launchSites = 3,
            valuation = 52000000000
        )
    }
}

package com.spacexwalk.utils

import okhttp3.mockwebserver.MockResponse

/**
 * Utils for mock web server
 *
 * Created by olegsheliakin on 25.01.2021.
 */

/**
 * Create [MockResponse] from file [path] and [responseCode]
 */
fun createResponse(path: String, responseCode: Int = 200) = MockResponse().apply {
    setBody(getResource(path).readText())
    setResponseCode(responseCode)
}

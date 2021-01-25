package com.spacexwalk.utils

import java.net.URL

/**
 * Created by olegsheliakin on 25.01.2021.
 */

/**
 * @see [Class.getResource]
 */
fun Any.getResource(path: String): URL = this::class.java.getResource(path)

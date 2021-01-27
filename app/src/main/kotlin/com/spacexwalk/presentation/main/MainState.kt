package com.spacexwalk.presentation.main

import com.spacexwalk.domain.entities.CompanyInfo
import com.spacexwalk.domain.entities.Launch

/**
 * Created by olegsheliakin on 27.01.2021.
 */
data class MainState(
    val launches: List<Launch> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
)

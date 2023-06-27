package com.example.home_api

import com.example.mytruth.core.featureApi.FeatureApi

interface HomeFeatureApi : FeatureApi {

    val homeNavRoute: String
}
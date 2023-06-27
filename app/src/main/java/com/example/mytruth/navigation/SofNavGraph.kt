package com.example.mytruth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.home_api.HomeFeatureApi
import com.example.home_impl.navigation.HomeFeatureImpl
import com.example.mytruth.core.featureApi.register

@Composable
fun sofNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val homeFeature: HomeFeatureApi = HomeFeatureImpl()

    NavHost(
        navController = navController,
        startDestination = homeFeature.homeNavRoute
    ) {
        register(
            featureApi = homeFeature,
            navController = navController,
            modifier = modifier
        )
    }
}
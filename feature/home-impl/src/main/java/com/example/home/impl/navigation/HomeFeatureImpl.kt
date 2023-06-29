package com.example.home.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.home.impl.ui.composable.PhotoScreen
import com.example.home_api.HomeFeatureApi

class HomeFeatureImpl : HomeFeatureApi {
    override val homeNavRoute: String = HOME_NAV

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = HOME_NAV,
            startDestination = HOME
        ) {
            composable(HOME) {
                PhotoScreen()
            }

        }

    }

    private companion object {
        const val HOME_NAV = "home_nav"
        const val HOME = "$HOME_NAV/home"
    }

}
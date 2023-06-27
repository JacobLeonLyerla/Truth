package com.example.home_impl.navigation

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
                Text(text = "whats up homie")
            }

        }

    }

    private companion object {
        const val HOME_NAV = "home_nav"
        const val HOME = "$HOME_NAV/home"
    }

}
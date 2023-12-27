package com.wahyouwebid.githubapp.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wahyouwebid.githubapp.features.detail.presentation.PopularDetailScreen
import com.wahyouwebid.githubapp.features.favourite.presentation.FavouriteScreen
import com.wahyouwebid.githubapp.features.popular.presentation.PopularScreen
import com.wahyouwebid.githubapp.features.splash.SplashScreen


@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToPopular = {
                    navController.navigate(Screen.Popular.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.Popular.route) {
            PopularScreen(
                navigateToDetail = {
                    navController.navigate("${Screen.PopularDetail.route}/$it")
                }
            )
        }

        composable(route = Screen.Favourite.route) {
            FavouriteScreen(
                navigateToDetail = {
                    navController.navigate("${Screen.PopularDetail.route}/$it")
                }
            )
        }

        composable(route = "${Screen.PopularDetail.route}/{login}") {
            PopularDetailScreen(
                navigationBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
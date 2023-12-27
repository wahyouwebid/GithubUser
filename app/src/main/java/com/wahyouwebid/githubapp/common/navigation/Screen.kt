package com.wahyouwebid.githubapp.common.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Popular : Screen("popular_screen")
    object PopularDetail : Screen("popular_detail_screen")
    object Favourite : Screen("favourite_screen")
    object Search : Screen("search_screen")
}
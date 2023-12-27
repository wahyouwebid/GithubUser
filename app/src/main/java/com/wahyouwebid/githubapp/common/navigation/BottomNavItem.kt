package com.wahyouwebid.githubapp.common.navigation

import com.wahyouwebid.githubapp.R
import com.wahyouwebid.githubapp.common.utils.Constants

sealed class BottomNavItem(
    val title: String,
    val image: Int,
    val route: String
) {
    object Popular : BottomNavItem(
        title = Constants.MENU_USER,
        image = R.drawable.ic_popular,
        route = Screen.Popular.route
    )

    object Favourite : BottomNavItem(
        title = Constants.MENU_FAVOURITE,
        image = R.drawable.ic_favourite,
        route = Screen.Favourite.route
    )

}

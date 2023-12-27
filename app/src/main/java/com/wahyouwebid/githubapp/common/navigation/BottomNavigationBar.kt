package com.wahyouwebid.githubapp.common.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wahyouwebid.githubapp.common.theme.ColorBlue
import com.wahyouwebid.githubapp.common.theme.ColorNavy
import com.wahyouwebid.githubapp.common.theme.ColorWhite

@Composable
fun BottomNavigationBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {

    val items = listOf(
        BottomNavItem.Popular,
        BottomNavItem.Favourite,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { _ ->
                    BottomNavigation(
                        backgroundColor = ColorWhite,
                        elevation = 8.dp
                    ) {
                        items.forEach { item ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(item.image),
                                        contentDescription = item.title
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.title,
                                        color = if (currentRoute == item.route) ColorNavy else ColorBlue
                                    )
                                },
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                selectedContentColor = ColorNavy,
                                unselectedContentColor = ColorBlue,
                                alwaysShowLabel = false
                            )
                        }
                    }
                }
            }
        }
    )
}
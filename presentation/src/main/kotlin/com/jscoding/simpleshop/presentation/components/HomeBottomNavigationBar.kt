package com.jscoding.simpleshop.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.jddev.simpletouch.ui.foundation.navigation.bottomnavbar.BottomNavItem
import com.jddev.simpletouch.ui.foundation.navigation.bottomnavbar.NavIcon
import com.jddev.simpletouch.ui.foundation.navigation.bottomnavbar.StUiBottomNavBar

enum class BottomNavTab(
    val route: String,
    val label: String,
    val filledIcon: ImageVector,
    val outlineIcon: ImageVector,
) {
    Catalog("nav_catalog", "Catalog", Icons.Default.Home, Icons.Outlined.Home),
    Search("nav_search", "Search", Icons.Filled.Search, Icons.Outlined.Search),
    Favorites("nav_favorites", "Favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    Profile("nav_profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

@Composable
fun HomeBottomNavBar(navController: NavHostController) {
    StUiBottomNavBar(
        navController = navController,
        items = BottomNavTab.entries.map {
            BottomNavItem(
                route = it.route,
                label = it.label,
                selectedIcon = NavIcon.Vector(it.filledIcon),
                unselectedIcon = NavIcon.Vector(it.outlineIcon),
            )
        }
    )
}
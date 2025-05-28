package com.jscoding.simpleshop.presentation.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiEnterAlwaysScrollBehavior
import com.jscoding.simpleshop.presentation.components.BottomNavTab
import com.jscoding.simpleshop.presentation.components.HomeBottomNavBar
import com.jscoding.simpleshop.presentation.home.catalog.CatalogScreenContent
import com.jscoding.simpleshop.presentation.home.favorite.FavoriteScreenContent
import com.jscoding.simpleshop.presentation.home.profile.ProfileScreenContent
import com.jscoding.simpleshop.presentation.home.search.SearchScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeNavController: NavHostController = rememberNavController(),
    navigateToProductDetail: (productId: Int) -> Unit,
) {
    val scrollBehavior = stUiEnterAlwaysScrollBehavior()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            when (currentRoute) {
                BottomNavTab.Catalog.route -> {
                    StUiTopAppBar(
                        scrollBehavior = scrollBehavior,
                        title = "Catalog"
                    )
                }
                null -> {
                    StUiTopAppBar(
                        scrollBehavior = scrollBehavior,
                        title = "Unexpected Error"
                    )
                }
                else -> {
                    StUiTopAppBar(
                        title = currentRoute.toString(),
                    )
                }
            }
        },
        bottomBar = {
            HomeBottomNavBar(navController = homeNavController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = homeNavController,
            startDestination = BottomNavTab.Catalog.route,
        ) {
            composable(BottomNavTab.Catalog.route) {
                CatalogScreenContent(
                    scrollBehavior = scrollBehavior,
                    navigateToProductDetail = navigateToProductDetail
                )
            }
            composable(BottomNavTab.Search.route) { SearchScreenContent() }
            composable(BottomNavTab.Favorites.route) { FavoriteScreenContent() }
            composable(BottomNavTab.Profile.route) { ProfileScreenContent() }
        }
    }
}
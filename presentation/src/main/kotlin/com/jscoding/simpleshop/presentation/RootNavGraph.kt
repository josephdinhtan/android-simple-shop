package com.jscoding.simpleshop.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.StUiDoubleBackHandler
import com.jddev.simpletouch.ui.navigation.StUiNavHost
import com.jscoding.simpleshop.presentation.home.HomeScreen

@Composable
fun RootNavGraph(
    rootNavController: NavHostController = rememberNavController(),
) {

    StUiDoubleBackHandler(
        toastMessage = "Press again to exit the app",
    )

    StUiNavHost(
        navController = rootNavController,
        startDestination = "nav_home",
    ) {
        composable("nav_home") {
            HomeScreen()
        }
    }
}
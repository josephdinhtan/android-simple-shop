package com.jscoding.simpleshop.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jddev.simpletouch.ui.foundation.StUiDoubleBackHandler
import com.jddev.simpletouch.ui.navigation.StUiNavHost
import com.jscoding.simpleshop.presentation.home.HomeScreen
import com.jscoding.simpleshop.presentation.productdetail.ProductDetailScreen

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
            HomeScreen(
                navigateToProductDetail = { productId ->
                    rootNavController.navigate("nav_product/$productId")
                }
            )
        }
        composable("nav_product/{product_id}",
            arguments = listOf(
                navArgument("product_id") {
                    type = NavType.IntType
                }
            )
        ) {
            ProductDetailScreen(
                onBack = {
                    rootNavController.navigateUp()
                }
            )
        }
    }
}
package com.jscoding.simpleshop.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.theme.StUiTheme

@Composable
fun SimpleShopApp() {
    StUiTheme(
        isDarkTheme = isSystemInDarkTheme(),
        useDynamicColors = false
    ) {
        val navController = rememberNavController()
        RootNavGraph(
            rootNavController = navController
        )
    }
}
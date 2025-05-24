package com.jscoding.simpleshop.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

enum class BottomNavTab(
    val route: String,
    val label: String,
    val filledIcon: ImageVector,
    val outlineIcon: ImageVector,
) {
    Catalog("nav_catalog", "Catalog", Icons.Filled.Home, Icons.Outlined.Home),
    Search("nav_search", "Search", Icons.Filled.Search, Icons.Outlined.Search),
    Favorites("nav_favorites", "Favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    Profile("nav_profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

@Composable
fun HomeBottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedTab = remember {
        mutableStateOf(BottomNavTab.entries.find { it.route == currentDestination?.route }
            ?: BottomNavTab.Catalog)
    }

    HomeBottomNavigationBar(
        selectedTab = selectedTab.value,
        onTabSelected = { tab ->
            navController.navigate(tab.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            selectedTab.value = tab
        }
    )
}

@Composable
private fun HomeBottomNavigationBar(
    selectedTab: BottomNavTab,
    onTabSelected: (BottomNavTab) -> Unit,
) {
    val colorScheme = MaterialTheme.colorScheme
    val surfaceColor = colorScheme.surface
    val selectedColor = colorScheme.onSurface
    val unselectedColor = colorScheme.onSurface.copy(alpha = 0.6f)

    Surface(
        color = surfaceColor,
//        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        tonalElevation = 8.dp,
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavTab.entries.forEach { tab ->
                val isSelected = tab == selectedTab
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { onTabSelected(tab) }
                        .padding(8.dp)) {
                    Icon(
                        imageVector = if (isSelected) tab.filledIcon else tab.outlineIcon,
                        contentDescription = tab.label,
                        tint = if (isSelected) selectedColor else unselectedColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = tab.label, style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) selectedColor else unselectedColor
                        )
                    )
                }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        val selectedTab = remember { mutableStateOf(BottomNavTab.Catalog) }
        HomeBottomNavigationBar(
            selectedTab = selectedTab.value,
            onTabSelected = { selectedTab.value = it })
    }
}
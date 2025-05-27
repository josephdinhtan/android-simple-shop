package com.jscoding.simpleshop.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jscoding.simpleshop.presentation.R

enum class BottomNavTab(
    val route: String,
    val label: String,
    val filledIcon: Int,
    val outlineIcon: Int,
) {
    Catalog("nav_catalog", "Catalog", R.drawable.ic_nav_home_outline, R.drawable.ic_nav_home_outline),
    Search("nav_search", "Search", R.drawable.ic_nav_search_outline, R.drawable.ic_nav_search_outline),
    Favorites("nav_favorites", "Favorites", R.drawable.ic_nav_favorite_outline, R.drawable.ic_nav_favorite_outline),
    Profile("nav_profile", "Profile", R.drawable.ic_nav_profile_outline, R.drawable.ic_nav_profile_outline)
}

@Composable
fun HomeBottomNavigationBar(navController: NavHostController, backgroundColor: Color = MaterialTheme.colorScheme.surface) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedTab = remember {
        mutableStateOf(BottomNavTab.entries.find { it.route == currentDestination?.route }
            ?: BottomNavTab.Catalog)
    }

    BottomNavigationBar(
        selectedTab = selectedTab.value,
        backgroundColor = backgroundColor,
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
private fun BottomNavigationBar(
    selectedTab: BottomNavTab,
    backgroundColor: Color,
    onTabSelected: (BottomNavTab) -> Unit,
) {
    val colorScheme = MaterialTheme.colorScheme
    val selectedColor = Color(0xFFFF9901)
    val unselectedColor = colorScheme.onSurface.copy(alpha = 0.6f)

    Surface(
        color = backgroundColor,
//        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
//        tonalElevation = 8.dp,
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Column {
            HorizontalDivider()
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavTab.entries.forEach { tab ->
                    val isSelected = tab == selectedTab
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(50))
                            .clickable { onTabSelected(tab) }
                    ) {
                        Icon(
                            painter = painterResource(if (isSelected) tab.filledIcon else tab.outlineIcon),
                            contentDescription = tab.label,
                            tint = if (isSelected) selectedColor else unselectedColor,
                            modifier = Modifier.size(30.dp)
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
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        val selectedTab = remember { mutableStateOf(BottomNavTab.Catalog) }
        BottomNavigationBar(
            selectedTab = selectedTab.value,
            backgroundColor = MaterialTheme.colorScheme.surface,
            onTabSelected = { selectedTab.value = it })
    }
}
package com.jscoding.simpleshop.presentation.home.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.jscoding.simpleshop.presentation.components.CategoryFilterSection
import com.jscoding.simpleshop.presentation.home.catalog.CatalogViewModel.Companion.allProductCategories

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CatalogScreenContent(
    catalogViewModel: CatalogViewModel = hiltViewModel(),
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToProductDetail: (productId: Int) -> Unit,
) {
    val products = catalogViewModel.productPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    val categories = catalogViewModel.categoriesState.collectAsState()
    val productsByCategory = catalogViewModel.productsByCategory.collectAsState()
    val selectedCategory = catalogViewModel.selectedCategory.collectAsState()

    LaunchedEffect(Unit) {
        catalogViewModel.getProductCategories()
    }

    Column {

        CategoryFilterSection(
            categories = categories.value,
            selectedCategories = setOf(selectedCategory.value),
            onCategoryToggle = {
                catalogViewModel.onProductCategorySelected(it)
            }
        )

        when (selectedCategory.value) {

            allProductCategories -> AllProductCatalogContent(
                scrollBehavior = scrollBehavior,
                products = products,
                navigateToProductDetail = navigateToProductDetail,
                onRefresh = {
                    val imageLoader = ImageLoader(context)
                    imageLoader.memoryCache?.clear()
                    imageLoader.diskCache?.clear()
                    products.refresh()
                }
            )

            else -> {
                CategoryProductCatalogContent(
                    scrollBehavior = scrollBehavior,
                    products = productsByCategory.value,
                    navigateToProductDetail = navigateToProductDetail,
                )
            }
        }
    }
}

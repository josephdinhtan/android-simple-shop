package com.jscoding.simpleshop.presentation.home.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.presentation.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductCatalogContent(
    scrollBehavior: TopAppBarScrollBehavior,
    products: List<Product>,
    navigateToProductDetail: (productId: Int) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            products[index].let { product ->
                ProductCard(product = product,
                    onClick = { navigateToProductDetail(product.id) })
            }
        }
    }
}
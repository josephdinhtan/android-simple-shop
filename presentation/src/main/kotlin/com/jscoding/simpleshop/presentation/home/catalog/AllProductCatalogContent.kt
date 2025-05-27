package com.jscoding.simpleshop.presentation.home.catalog

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.presentation.components.ProductCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProductCatalogContent(
    scrollBehavior: TopAppBarScrollBehavior,
    products: LazyPagingItems<Product>,
    navigateToProductDetail: (productId: Int) -> Unit,
    onRefresh: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = products.loadState) {
        if (products.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (products.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column {
        PullToRefreshBox(
            isRefreshing = products.loadState.refresh is LoadState.Loading,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (products.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    verticalItemSpacing = 8.dp,
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(products.itemCount) { index ->
                        products[index]?.let { product ->
                            ProductCard(product = product,
                                onClick = { navigateToProductDetail(product.id) })
                        }
                    }
                    item(span = StaggeredGridItemSpan.FullLine) {
                        val loadState = products.loadState.append
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(48.dp),  // Place holder size
                            contentAlignment = Alignment.Center
                        ) {
                            when (loadState) {
                                is LoadState.Loading -> {
                                    CircularProgressIndicator()
                                }

                                is LoadState.Error -> {
                                    Text("Lỗi khi tải thêm")
                                }

                                else -> {
                                    Spacer(modifier = Modifier) // place holder view
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
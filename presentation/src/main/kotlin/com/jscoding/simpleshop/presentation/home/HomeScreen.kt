package com.jscoding.simpleshop.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.presentation.components.ProductCard
import com.jscoding.simpleshop.presentation.utils.getPreviewProduct
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val products = viewModel.productPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    HomeScreen(
        products = products,
        onRefresh = {
            val imageLoader = ImageLoader(context)
            imageLoader.memoryCache?.clear()
            imageLoader.diskCache?.clear()
            products.refresh()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    products: LazyPagingItems<Product>,
    onRefresh: () -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            StUiTopAppBar(
                title = "Home"
            )
        }
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

        PullToRefreshBox(
            isRefreshing = products.loadState.refresh is LoadState.Loading,
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(it)
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
                        .padding(8.dp),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(products.itemCount) { index ->
                        products[index]?.let { product ->
                            ProductCard(product = product)
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

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        HomeScreen(
            products = flowOf(
                PagingData.from(
                    listOf(
                        getPreviewProduct(),
                        getPreviewProduct(),
                        getPreviewProduct()
                    )
                )
            ).collectAsLazyPagingItems(),
            onRefresh = {}
        )
    }
}
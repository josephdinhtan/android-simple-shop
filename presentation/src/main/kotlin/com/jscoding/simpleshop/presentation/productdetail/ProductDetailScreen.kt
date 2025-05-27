package com.jscoding.simpleshop.presentation.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jddev.simpletouch.ui.foundation.StUiScaffold
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jscoding.simpleshop.domain.model.ProductDetail
import com.jscoding.simpleshop.presentation.utils.getPreviewProductDetail

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {
    val uiState = viewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        is ProductDetailViewModel.UiState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Something went wrong")
                Text(text = state.message)
            }
        }

        ProductDetailViewModel.UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is ProductDetailViewModel.UiState.Success -> ProductDetailScreen(
            productDetail = state.productDetail, onBack = onBack
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailScreen(
    productDetail: ProductDetail,
    onBack: () -> Unit,
) {
    StUiScaffold(
        topBar = {
            StUiTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                title = "",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.FavoriteBorder, "Favorite")
                    }
                },
                onBack = onBack,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                item {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(productDetail.images[0]).crossfade(true)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .memoryCachePolicy(CachePolicy.ENABLED).build(),
                        contentDescription = productDetail.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .aspectRatio(1f),
                        contentScale = ContentScale.FillWidth
                    )
                }

                item {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, "Star", tint = Color(0xFFFB6C0A))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = productDetail.rating.toString(),
                            modifier = Modifier.padding(start = 4.dp),
                            color = Color(0xFFFB6C0A)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Surface(
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(100),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(
                                "${productDetail.reviews.size} reviews",
                                modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
                            )
                        }
                    }
                }

                item {
                    Text(
                        productDetail.title,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                item {
                    Text(
                        productDetail.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            HorizontalDivider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "$${productDetail.price}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(1f),
                )
                Button(onClick = {}) {
                    Text("Add to cart")
                }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        ProductDetailScreen(productDetail = getPreviewProductDetail(), onBack = {})
    }
}
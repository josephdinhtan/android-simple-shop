package com.jscoding.simpleshop.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jddev.simpletouch.ui.foundation.StUiScaffold
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductDetail

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {
    val uiState = viewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        is ProductViewModel.UiState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Something went wrong")
                Text(text = state.message)
            }
        }

        ProductViewModel.UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is ProductViewModel.UiState.Success -> ProductScreen(
            productDetail = state.productDetail,
            onBack = onBack
        )
    }
}

@Composable
private fun ProductScreen(
    productDetail: ProductDetail,
    onBack: () -> Unit,
) {
    StUiScaffold(
        onBack = onBack
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(productDetail.images[0])
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = productDetail.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
    }
}
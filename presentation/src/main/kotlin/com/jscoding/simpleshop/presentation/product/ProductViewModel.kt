package com.jscoding.simpleshop.presentation.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jscoding.simpleshop.domain.model.ProductDetail
import com.jscoding.simpleshop.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductByIdUseCase: GetProductDetailUseCase
): ViewModel() {

    sealed interface UiState {
        data object Loading : UiState
        data class Success(val productDetail: ProductDetail) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        val productId: Int? = savedStateHandle["product_id"]
        if (productId == null) {
            _uiState.value = UiState.Error("Invalid product ID")
        } else {
            _uiState.value = UiState.Loading
            loadProduct(productId)
        }
    }

    private fun loadProduct(productId: Int) {
        viewModelScope.launchIo {
            val productDetail = getProductByIdUseCase(productId)
            if (productDetail != null) {
                _uiState.value = UiState.Success(productDetail)
            } else {
                _uiState.value = UiState.Error("Product not found")
            }
        }
    }

    private fun CoroutineScope.launchIo(block: suspend () -> Unit) {
        launch(Dispatchers.IO) {
            block()
        }
    }
}
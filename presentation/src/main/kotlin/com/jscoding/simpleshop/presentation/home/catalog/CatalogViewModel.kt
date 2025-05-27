package com.jscoding.simpleshop.presentation.home.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductCategory
import com.jscoding.simpleshop.domain.usecase.GetPagedProductsUseCase
import com.jscoding.simpleshop.domain.usecase.GetProductCategoriesUseCase
import com.jscoding.simpleshop.domain.usecase.GetProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedProductsUseCase,
    private val getProductCategoriesUseCase: GetProductCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase
) : ViewModel() {
    val productPagingFlow = getPagedProductsUseCase().cachedIn(viewModelScope)

    private val _categoriesState = MutableStateFlow<List<ProductCategory>>(emptyList())
    val categoriesState = _categoriesState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<ProductCategory>(allProductCategories)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _productsByCategory = MutableStateFlow<List<Product>>(emptyList())
    val productsByCategory = _productsByCategory.asStateFlow()

    fun getProductCategories() {
        viewModelScope.launch {
            _categoriesState.value = listOf(allProductCategories) + getProductCategoriesUseCase()
        }
    }

    fun onProductCategorySelected(category: ProductCategory) {
        viewModelScope.launch {
            _selectedCategory.value = category
            _productsByCategory.value = getProductsByCategoryUseCase(category.slug)
        }
    }

    companion object {
        val allProductCategories = ProductCategory("all-product", "All Product", "")
    }
}
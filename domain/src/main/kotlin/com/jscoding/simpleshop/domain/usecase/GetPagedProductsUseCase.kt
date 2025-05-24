package com.jscoding.simpleshop.domain.usecase

import androidx.paging.PagingData
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetPagedProductsUseCase (
    private val repository: ProductRepository,
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getPagedProducts()
    }
}
package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts(1, 1)
    }
}
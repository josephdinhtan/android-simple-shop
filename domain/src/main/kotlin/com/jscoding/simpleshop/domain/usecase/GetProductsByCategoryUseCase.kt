package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.ProductRepository

class GetProductsByCategoryUseCase(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(categoryId: String): List<Product> {
        return repository.getProductsByCategory(categoryId)
    }
}
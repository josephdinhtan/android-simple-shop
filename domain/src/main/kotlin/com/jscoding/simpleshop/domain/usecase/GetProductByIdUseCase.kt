package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return repository.getProductById(id)
    }
}
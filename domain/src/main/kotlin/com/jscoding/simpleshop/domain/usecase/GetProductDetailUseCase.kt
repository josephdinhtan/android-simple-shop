package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.ProductDetail
import com.jscoding.simpleshop.domain.repository.ProductRepository

class GetProductDetailUseCase(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(id: Int): ProductDetail? {
        return repository.getProductDetailById(id)
    }
}
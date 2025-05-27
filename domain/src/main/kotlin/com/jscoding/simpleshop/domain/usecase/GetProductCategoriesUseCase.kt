package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.ProductCategory
import com.jscoding.simpleshop.domain.repository.ProductRepository

class GetProductCategoriesUseCase(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(): List<ProductCategory> {
        return repository.getProductCategories()
    }
}
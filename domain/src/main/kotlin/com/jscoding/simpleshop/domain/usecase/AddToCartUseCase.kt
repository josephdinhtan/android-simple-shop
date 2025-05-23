package com.jscoding.simpleshop.domain.usecase

import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.CartRepository

class AddToCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.addToCart(product)
    }
}
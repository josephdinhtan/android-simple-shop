package com.jscoding.simpleshop.domain.repository

import com.jscoding.simpleshop.domain.model.Product

interface CartRepository {
    suspend fun addToCart(product: Product)
    suspend fun removeFromCart(productId: Int)
    suspend fun getCartItems(): List<Product>
    suspend fun clearCart()
}
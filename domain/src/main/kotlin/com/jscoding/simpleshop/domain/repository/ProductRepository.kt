package com.jscoding.simpleshop.domain.repository

import androidx.paging.PagingData
import com.jscoding.simpleshop.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(page: Int, pageSize: Int): Flow<List<Product>>
    fun getPagedProducts(): Flow<PagingData<Product>>
    suspend fun getProductById(id: Int): Product
    suspend fun getProductsByCategory(category: String): List<Product>
}
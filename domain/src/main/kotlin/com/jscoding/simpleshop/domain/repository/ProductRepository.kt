package com.jscoding.simpleshop.domain.repository

import androidx.paging.PagingData
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductCategory
import com.jscoding.simpleshop.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getPagedProducts(): Flow<PagingData<Product>>
    suspend fun getProductDetailById(id: Int): ProductDetail?
    suspend fun getProductCategories(): List<ProductCategory>
    suspend fun getProductsByCategory(category: String): List<Product>
}
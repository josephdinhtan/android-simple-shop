package com.jscoding.simpleshop.data.local.productdetail

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductDetailDao {
    @Upsert
    suspend fun upsertAll(productDetails: List<ProductDetailEntity>)

    @Upsert
    suspend fun upsert(productDetail: ProductDetailEntity)

    @Query("SELECT * FROM product_details WHERE id = :id")
    suspend fun getProductById(id: Int): ProductDetailEntity?

    @Query("DELETE FROM product_details WHERE cachedAt < :threshold")
    suspend fun deleteOlderThan(threshold: Long)

    @Query("DELETE FROM product_details")
    suspend fun clearAll()
}
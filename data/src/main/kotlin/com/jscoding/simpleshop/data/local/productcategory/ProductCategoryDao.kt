package com.jscoding.simpleshop.data.local.productcategory

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductCategoryDao {

    @Query("SELECT * FROM product_categories")
    suspend fun getAll(): List<ProductCategoryEntity>

    @Upsert
    suspend fun upsertAll(categories: List<ProductCategoryEntity>)

    @Upsert
    suspend fun upsert(category: ProductCategoryEntity)

    @Query("SELECT * FROM product_categories WHERE slug = :slug")
    suspend fun getBySlug(slug: String): ProductCategoryEntity?

    @Query("DELETE FROM product_categories WHERE slug = :slug")
    suspend fun deleteBySlug(slug: String)

    @Query("DELETE FROM product_categories WHERE cachedAt < :threshold")
    suspend fun deleteOlderThan(threshold: Long)

    @Query("DELETE FROM product_categories")
    suspend fun clearAll()
}
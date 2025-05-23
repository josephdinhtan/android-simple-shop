package com.jscoding.simpleshop.data.local.product

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun pagingSource(): PagingSource<Int, ProductEntity>

    @Query("DELETE FROM products")
    suspend fun clearAll()
}
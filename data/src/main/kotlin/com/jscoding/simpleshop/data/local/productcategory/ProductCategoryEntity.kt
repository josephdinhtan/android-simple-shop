package com.jscoding.simpleshop.data.local.productcategory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_categories")
data class ProductCategoryEntity(
    @PrimaryKey
    val slug: String,
    val name: String,
    val url: String,
    val cachedAt: Long
)

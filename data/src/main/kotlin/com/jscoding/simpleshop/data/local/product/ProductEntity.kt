package com.jscoding.simpleshop.data.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val thumbnailUrl: String,
    val rating: Double,
)
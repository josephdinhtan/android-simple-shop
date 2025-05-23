package com.jscoding.simpleshop.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val category: String,
    val rating: Double
)

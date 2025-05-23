package com.jscoding.simpleshop.data.remote

data class ProductsApiResponse(
    val products: List<ProductDto>, // This list will use your existing ProductDto
    val total: Int,
    val skip: Int,
    val limit: Int
)
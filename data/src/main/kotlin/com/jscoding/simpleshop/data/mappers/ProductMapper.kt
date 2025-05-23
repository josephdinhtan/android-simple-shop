package com.jscoding.simpleshop.data.mappers

import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.remote.ProductDto
import com.jscoding.simpleshop.domain.model.Product

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = thumbnail,
        rating = rating
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = imageUrl,
        rating = rating
    )
}
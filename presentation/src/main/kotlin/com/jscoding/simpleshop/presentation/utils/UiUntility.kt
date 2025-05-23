package com.jscoding.simpleshop.presentation.utils

import com.jscoding.simpleshop.domain.model.Product

internal fun getPreviewProduct(): Product {
    return Product(
        id = 1,
        title = "Product Tittle",
        price = 10.0,
        description = "Product Description",
        category = "Category",
        imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        rating = 4.5
    )
}
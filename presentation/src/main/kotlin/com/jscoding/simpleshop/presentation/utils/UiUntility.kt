package com.jscoding.simpleshop.presentation.utils

import com.jscoding.simpleshop.domain.model.Dimensions
import com.jscoding.simpleshop.domain.model.Meta
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductDetail

internal fun getPreviewProduct(): Product {
    return Product(
        id = 1,
        title = "Product Tittle",
        price = 10.0,
        category = "Category",
        thumbnailUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        rating = 4.5
    )
}

internal fun getPreviewProductDetail(): ProductDetail {
    return ProductDetail(
        id = 1,
        title = "Product Tittle",
        description = "Product Description",
        category = "Category",
        price = 10.0,
        discountPercentage = 10.0,
        rating = 4.5,
        stock = 10,
        tags = listOf("Tag 1", "Tag 2"),
        brand = "Brand",
        sku = "SKU",
        weight = 10,
        dimensions = Dimensions(10.0, 10.0, 10.0),
        warrantyInformation = "Warranty Information",
        shippingInformation = "Shipping Information",
        availabilityStatus = "Availability Status",
        reviews = listOf(),
        returnPolicy = "Return Policy",
        minimumOrderQuantity = 1,
        meta = Meta(
            createdAt = "2023-04-01T00:00:00.000Z",
            updatedAt = "2023-04-01T00:00:00.000Z",
            barcode = "Barcode",
            qrCode = "QR Code"
        ),
        images = listOf("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"),
        thumbnail = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
    )
}
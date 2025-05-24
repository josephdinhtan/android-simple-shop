package com.jscoding.simpleshop.data.mappers

import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.productdetail.DimensionsEntity
import com.jscoding.simpleshop.data.local.productdetail.MetaEntity
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailEntity
import com.jscoding.simpleshop.data.local.productdetail.ReviewEntity
import com.jscoding.simpleshop.data.remote.product.ProductDto
import com.jscoding.simpleshop.domain.model.Dimensions
import com.jscoding.simpleshop.domain.model.Meta
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductDetail
import com.jscoding.simpleshop.domain.model.Review

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        category = category,
        thumbnailUrl = thumbnail,
        rating = rating
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        category = category,
        thumbnailUrl = thumbnailUrl,
        rating = rating
    )
}

fun ProductDetailEntity.toProductDetail(): ProductDetail {
    return ProductDetail(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        tags = tags,
        brand = brand,
        sku = sku,
        weight = weight,
        dimensions = Dimensions(
            width = dimensions.width, height = dimensions.height, depth = dimensions.depth
        ),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews.map {
            Review(
                rating = it.rating,
                comment = it.comment,
                date = it.date,
                reviewerName = it.reviewerName,
                reviewerEmail = it.reviewerEmail
            )
        },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        meta = Meta(
            createdAt = meta.createdAt,
            updatedAt = meta.updatedAt,
            barcode = meta.barcode,
            qrCode = meta.qrCode
        ),
        images = images,
        thumbnail = thumbnail
    )
}

fun ProductDto.toProductDetailEntity(cachedAt: Long): ProductDetailEntity {
    return ProductDetailEntity(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        tags = tags,
        brand = brand,
        sku = sku,
        weight = weight,
        dimensions = DimensionsEntity(
            width = dimensions.width, height = dimensions.height, depth = dimensions.depth
        ),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews.map {
            ReviewEntity(
                rating = it.rating,
                comment = it.comment,
                date = it.date,
                reviewerName = it.reviewerName,
                reviewerEmail = it.reviewerEmail
            )
        },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        meta = MetaEntity(
            createdAt = meta.createdAt,
            updatedAt = meta.updatedAt,
            barcode = meta.barcode,
            qrCode = meta.qrCode
        ),
        images = images,
        thumbnail = thumbnail,
        cachedAt = cachedAt
    )
}
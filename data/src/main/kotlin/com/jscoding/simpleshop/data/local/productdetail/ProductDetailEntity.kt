package com.jscoding.simpleshop.data.local.productdetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_details")
data class ProductDetailEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String?,
    val sku: String,
    val weight: Int,
    val dimensions: DimensionsEntity,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<ReviewEntity>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val meta: MetaEntity,
    val images: List<String>,
    val thumbnail: String,
    val cachedAt: Long,
)

data class DimensionsEntity(
    val width: Double,
    val height: Double,
    val depth: Double
)

data class ReviewEntity(
    val rating: Int,
    val comment: String,
    val date: String, // Consider parsing to Date/LocalDateTime if needed
    val reviewerName: String,
    val reviewerEmail: String
)

data class MetaEntity(
    val createdAt: String, // Consider parsing
    val updatedAt: String, // Consider parsing
    val barcode: String,
    val qrCode: String
)
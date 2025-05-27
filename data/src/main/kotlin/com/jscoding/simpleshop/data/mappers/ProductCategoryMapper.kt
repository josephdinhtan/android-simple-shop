package com.jscoding.simpleshop.data.mappers

import com.jscoding.simpleshop.data.local.productcategory.ProductCategoryEntity
import com.jscoding.simpleshop.data.remote.product.ProductCategoryDto
import com.jscoding.simpleshop.domain.model.ProductCategory

fun ProductCategoryEntity.toProductCategory() = ProductCategory(
    slug = slug,
    name = name,
    url = url
)

fun ProductCategoryDto.toProductCategoryEntities(cachedAt: Long) = ProductCategoryEntity(
    slug = slug,
    name = name,
    url = url,
    cachedAt = cachedAt
)
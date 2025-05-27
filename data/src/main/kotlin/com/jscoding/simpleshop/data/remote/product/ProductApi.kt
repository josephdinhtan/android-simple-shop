package com.jscoding.simpleshop.data.remote.product

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductsApiResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @GET("products/categories")
    suspend fun getProductCategories(): List<ProductCategoryDto>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): ProductsApiResponse

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}
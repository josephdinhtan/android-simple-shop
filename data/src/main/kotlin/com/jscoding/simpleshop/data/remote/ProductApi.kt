package com.jscoding.simpleshop.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductsApiResponse

    suspend fun getProductById(id: Int): ProductDto
    suspend fun getProductsByCategory(category: String): List<ProductDto>

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}
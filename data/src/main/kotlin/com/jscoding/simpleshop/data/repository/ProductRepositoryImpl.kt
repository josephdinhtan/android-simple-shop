package com.jscoding.simpleshop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.productcategory.ProductCategoryDao
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailDao
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailEntity
import com.jscoding.simpleshop.data.mappers.toProduct
import com.jscoding.simpleshop.data.mappers.toProductCategory
import com.jscoding.simpleshop.data.mappers.toProductCategoryEntities
import com.jscoding.simpleshop.data.mappers.toProductDetail
import com.jscoding.simpleshop.data.mappers.toProductDetailEntity
import com.jscoding.simpleshop.data.remote.product.ProductApi
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.model.ProductCategory
import com.jscoding.simpleshop.domain.model.ProductDetail
import com.jscoding.simpleshop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productDetailDao: ProductDetailDao,
    private val productCategoryDao: ProductCategoryDao,
    private val pager: Pager<Int, ProductEntity>,
) : ProductRepository {

    override fun getPagedProducts(): Flow<PagingData<Product>> {
        return pager.flow.map { pagingData ->
            pagingData.map { it.toProduct() }
        }
    }

    override suspend fun getProductDetailById(id: Int): ProductDetail? {
        // Step 1: Delete all stale entries
        val oneDayMillis = 24 * 60 * 60 * 1000L
        val staleThreshold = System.currentTimeMillis() - oneDayMillis
        productDetailDao.deleteOlderThan(staleThreshold)

        // Step 2: Try to get from DB
        val cached = productDetailDao.getProductById(id)
        if (cached != null) {
            return cached.toProductDetail()
        }

        // Step 3: Fetch from API
        return try {
            val dto = productApi.getProductById(id)
            val entity = dto.toProductDetailEntity(System.currentTimeMillis())
            productDetailDao.upsert(entity)
            entity.toProductDetail()
        } catch (e: Exception) {
            null // Or fallback to cached if it exists
        }
    }

    override suspend fun getProductCategories(): List<ProductCategory> {
        val oneDayMillis = 24 * 60 * 60 * 1000L
        val threshold = System.currentTimeMillis() - oneDayMillis
        productCategoryDao.deleteOlderThan(threshold)

        val cached = productCategoryDao.getAll()
        if (cached.isNotEmpty()) {
            return cached.map { it.toProductCategory() }
        }

        return try {
            val remote = productApi.getProductCategories()
            val now = System.currentTimeMillis()
            val entities = remote.map { it.toProductCategoryEntities(now) }
            productCategoryDao.upsertAll(entities)
            entities.map { it.toProductCategory() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        return try {
            val response = productApi.getProductsByCategory(category)
            response.products.map { it.toProduct() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun isStale(entity: ProductDetailEntity): Boolean {
        val oneDayMillis = 24 * 60 * 60 * 1000L
        return System.currentTimeMillis() - entity.cachedAt > oneDayMillis
    }
}
package com.jscoding.simpleshop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.jscoding.simpleshop.data.local.product.ProductDao
import com.jscoding.simpleshop.data.local.product.ProductDatabase
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.mappers.toProduct
import com.jscoding.simpleshop.data.remote.ProductRemoteMediator
import com.jscoding.simpleshop.domain.model.Product
import com.jscoding.simpleshop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
//    private val productDao: ProductDao,
//    private val remoteMediator: ProductRemoteMediator,
//    private val database: ProductDatabase,
    private val pager: Pager<Int, ProductEntity>
) : ProductRepository {
    override fun getProducts(page: Int, pageSize: Int): Flow<List<Product>> {
        TODO("Not yet implemented")
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            remoteMediator = remoteMediator,
//            pagingSourceFactory = { productDao.pagingSource() }
//        ).flow
//            .map { pagingData ->
//                // Collect the paging data into a full list
//                val productList = mutableListOf<Product>()
//                pagingData.collect { item ->
//                    productList.add(item.toDomain())
//                }
//                productList
//            }
    }

    override fun getPagedProducts(): Flow<PagingData<Product>> {
        return pager.flow.map { pagingData ->
            pagingData.map { it.toProduct() }
        }
    }

    override suspend fun getProductById(id: Int): Product {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        TODO("Not yet implemented")
    }
}
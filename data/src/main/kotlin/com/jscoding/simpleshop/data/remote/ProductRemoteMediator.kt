package com.jscoding.simpleshop.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jscoding.simpleshop.data.local.product.ProductDatabase
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.remotekey.RemoteKeyEntity
import com.jscoding.simpleshop.data.mappers.toProductEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val productApi: ProductApi,
    private val productDb: ProductDatabase,
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>,
    ): MediatorResult {
        Timber.d("loadType = $loadType")
        val productDao = productDb.productDao()
        val remoteKeysDao = productDb.remoteKeysDao()

        val page = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> {
                val firstItem = state.firstItemOrNull() ?: return MediatorResult.Success(true)
                val remoteKey = remoteKeysDao.getRemoteKeyProductId(firstItem.id)
                remoteKey?.prevKey ?: return MediatorResult.Success(true)
            }
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                val remoteKey = remoteKeysDao.getRemoteKeyProductId(lastItem.id)
                remoteKey?.nextKey ?: return MediatorResult.Success(true)
            }
        }
        try {
            val response = productApi.getProducts(limit = state.config.pageSize, skip = page)
            val products = response.products.map { it.toProductEntity() }

            delay(2000L)
            productDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    productDao.clearAll()
                }

                val keys = products.map {
                    RemoteKeyEntity(
                        productId = it.id,
                        prevKey = if (page == 0) null else page - state.config.pageSize,
                        nextKey = if (products.isEmpty()) null else page + state.config.pageSize
                    )
                }

                remoteKeysDao.upsertAll(keys)
                productDao.upsertAll(products)
            }
            return MediatorResult.Success(endOfPaginationReached = products.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}
package com.jscoding.simpleshop.di.data

import androidx.paging.Pager
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailDao
import com.jscoding.simpleshop.data.remote.product.ProductApi
import com.jscoding.simpleshop.data.repository.ProductRepositoryImpl
import com.jscoding.simpleshop.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        pager: Pager<Int, ProductEntity>,
        productApi: ProductApi,
        productDetailDao: ProductDetailDao
    ): ProductRepository {
        return ProductRepositoryImpl(
            productApi,
            productDetailDao,
            pager
        )
    }
}
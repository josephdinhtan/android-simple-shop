package com.jscoding.simpleshop.di.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.jscoding.simpleshop.data.local.ProductDatabase
import com.jscoding.simpleshop.data.local.product.ProductDao
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailDao
import com.jscoding.simpleshop.data.remote.ProductRemoteMediator
import com.jscoding.simpleshop.data.remote.product.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "shop_products_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(db: ProductDatabase): ProductDao = db.productDao()

    @Provides
    @Singleton
    fun provideProductDetailDao(db: ProductDatabase): ProductDetailDao = db.productDetailDao()

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideProductPager(
        productDb: ProductDatabase,
        productApi: ProductApi,
    ): Pager<Int, ProductEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = ProductRemoteMediator(
                productDb = productDb,
                productApi = productApi
            ),
            pagingSourceFactory = {
                productDb.productDao().pagingSource()
            }
        )
    }
}
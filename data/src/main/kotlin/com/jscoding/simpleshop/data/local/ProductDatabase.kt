package com.jscoding.simpleshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jscoding.simpleshop.data.local.product.ProductDao
import com.jscoding.simpleshop.data.local.product.ProductEntity
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailConverter
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailDao
import com.jscoding.simpleshop.data.local.productdetail.ProductDetailEntity
import com.jscoding.simpleshop.data.local.remotekey.RemoteKeyEntity
import com.jscoding.simpleshop.data.local.remotekey.RemoteKeyDao

@Database(
    entities = [
        ProductEntity::class,
        RemoteKeyEntity::class,
        ProductDetailEntity::class
    ],
    version = 1
)
@TypeConverters(ProductDetailConverter::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun remoteKeysDao(): RemoteKeyDao
    abstract fun productDetailDao(): ProductDetailDao
}
package com.jscoding.simpleshop.data.local.product

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jscoding.simpleshop.data.local.remotekey.RemoteKeyEntity
import com.jscoding.simpleshop.data.local.remotekey.RemoteKeyDao

@Database(
    entities = [ProductEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun remoteKeysDao(): RemoteKeyDao
}
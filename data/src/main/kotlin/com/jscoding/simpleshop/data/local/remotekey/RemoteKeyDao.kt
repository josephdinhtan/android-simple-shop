package com.jscoding.simpleshop.data.local.remotekey

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_keys WHERE productId = :id")
    suspend fun getRemoteKeyProductId(id: Int): RemoteKeyEntity?

    @Upsert
    suspend fun upsertAll(remoteKeys: List<RemoteKeyEntity>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
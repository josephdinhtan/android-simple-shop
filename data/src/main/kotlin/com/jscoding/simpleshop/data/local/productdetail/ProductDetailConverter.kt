package com.jscoding.simpleshop.data.local.productdetail

import androidx.room.TypeConverter
import com.google.gson.Gson

class ProductDetailConverter {
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        return objects.toList()
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toDimensionsEntity(value: String): DimensionsEntity {
        return Gson().fromJson(value, DimensionsEntity::class.java)
    }

    @TypeConverter
    fun fromDimensionsEntity(dimensions: DimensionsEntity): String {
        return Gson().toJson(dimensions)
    }

    @TypeConverter
    fun toReviewEntityList(value: String): List<ReviewEntity> {
        val objects = Gson().fromJson(value, Array<ReviewEntity>::class.java) as Array<ReviewEntity>
        return objects.toList()
    }

    @TypeConverter
    fun fromReviewEntityList(list: List<ReviewEntity>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toMetaEntity(value: String): MetaEntity {
        return Gson().fromJson(value, MetaEntity::class.java)
    }

    @TypeConverter
    fun fromMetaEntity(meta: MetaEntity): String {
        return Gson().toJson(meta)
    }
}
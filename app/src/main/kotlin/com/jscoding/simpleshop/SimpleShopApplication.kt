package com.jscoding.simpleshop

import android.app.Application
import com.jddev.simpletouch.utils.logging.AppTree
import com.jscoding.simpleshop.data.local.product.ProductDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SimpleShopApplication  : Application() {

    @Inject
    lateinit var appTree: AppTree

    @Inject
    lateinit var appDatabase: ProductDatabase

    override fun onCreate() {
        super.onCreate()
    }
}
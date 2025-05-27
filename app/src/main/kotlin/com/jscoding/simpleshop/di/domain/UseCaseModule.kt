package com.jscoding.simpleshop.di.domain

import com.jscoding.simpleshop.domain.repository.CartRepository
import com.jscoding.simpleshop.domain.repository.ProductRepository
import com.jscoding.simpleshop.domain.usecase.AddToCartUseCase
import com.jscoding.simpleshop.domain.usecase.GetPagedProductsUseCase
import com.jscoding.simpleshop.domain.usecase.GetProductCategoriesUseCase
import com.jscoding.simpleshop.domain.usecase.GetProductDetailUseCase
import com.jscoding.simpleshop.domain.usecase.GetProductsByCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetProductUseCase(
        repository: ProductRepository
    ): GetPagedProductsUseCase {
        return GetPagedProductsUseCase(repository)
    }

    @Provides
    fun provideGetProductDetailUseCase(
        repository: ProductRepository
    ): GetProductDetailUseCase {
        return GetProductDetailUseCase(repository)
    }

    @Provides
    fun provideAddToCartUseCase(
        cartRepository: CartRepository
    ): AddToCartUseCase {
        return AddToCartUseCase(cartRepository)
    }

    @Provides
    fun provideGetProductCategoriesUseCase(
        repository: ProductRepository
    ): GetProductCategoriesUseCase {
        return GetProductCategoriesUseCase(repository)
    }

    @Provides
    fun provideGetProductsByCategoryUseCase(
        repository: ProductRepository
    ): GetProductsByCategoryUseCase {
        return GetProductsByCategoryUseCase(repository)
    }
}
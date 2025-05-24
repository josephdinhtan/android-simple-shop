package com.jscoding.simpleshop.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jscoding.simpleshop.domain.usecase.GetPagedProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedProductsUseCase,
) : ViewModel() {
    val productPagingFlow = getPagedProductsUseCase().cachedIn(viewModelScope)
}
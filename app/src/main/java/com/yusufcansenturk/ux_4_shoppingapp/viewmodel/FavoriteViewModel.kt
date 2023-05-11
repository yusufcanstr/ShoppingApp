package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val basketRepository: BasketRepository
) : ViewModel() {

    var allData: MutableLiveData<List<FavoriteData>> = MutableLiveData()

    init {
        loadRecords()
    }

    fun deleteProductsFavorite(product_id :Int) {
        viewModelScope.launch {
            repository.deleteProductFavorite(product_id)
            loadRecords()
        }
    }

    private fun loadRecords() {
        viewModelScope.launch {
            val list = repository.readAllData
            allData.postValue(list)
        }
    }

    fun addProductBasket(favoriteData: FavoriteData) {
        viewModelScope.launch {
            basketRepository.addBasketProduct(repository.convertFavoriteToBasket(favoriteData))
        }
    }

}
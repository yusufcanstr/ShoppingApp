package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketData
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private var repository: BasketRepository
) : ViewModel() {

    private var _basketList = MutableLiveData<List<BasketData>>()
    val basketList: LiveData<List<BasketData>> get() = _basketList
    private var _basketPrice = MutableLiveData<Double>()
    val basketPrice: LiveData<Double> get() = _basketPrice

    init {
        loadRecords()
    }

    fun getList(context: Context) {
        viewModelScope.launch {
            _basketList.value = repository.readAllData
            getBasketPrice()
        }
    }

    fun addProductsBasket(basketData: BasketData) {
        viewModelScope.launch {
            repository.addBasketProduct(basketData)
            loadRecords()
        }
    }

    fun deleteProductsBasket(basketData: BasketData) {
        viewModelScope.launch {
            repository.deleteProductBasket(basketData)
            loadRecords()
        }
    }

    private fun loadRecords() {
        viewModelScope.launch {
            val list = repository.readAllData
            _basketList.postValue(list)
        }
    }

    private fun getBasketPrice() {
        viewModelScope.launch {
            basketList.value?.let { list ->
                _basketPrice.value = repository.totalPriceProducts(list)
            }
        }
    }

    fun increaseQuantity(basketData: BasketData) {
        viewModelScope.launch {
            repository.increaseQuantity(basketData)
        }
    }

    fun decreaseQuantity(basketData: BasketData) {
        viewModelScope.launch {
            repository.decreaseQuantity(basketData)
        }
    }

}
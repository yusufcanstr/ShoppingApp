package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val basketRepository: BasketRepository
) : ViewModel() {

    var allData: MutableLiveData<List<FavoriteData>> = MutableLiveData()

    private var initialSureList = listOf<FavoriteData>()
    private var isSearchStarting = true

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

    fun searchSureList(query: String) {
        val listToSearch = if (isSearchStarting) {
            allData.value ?: return
        } else {
            initialSureList
        } ?: return

        viewModelScope.launch(Dispatchers.Main) {
            if (query.isEmpty()){
                allData.value = initialSureList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch!!.filter {
                it.title!!.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarting) {
                initialSureList = allData.value!!
                isSearchStarting = false
            }
            allData.value = results
        }

    }



}
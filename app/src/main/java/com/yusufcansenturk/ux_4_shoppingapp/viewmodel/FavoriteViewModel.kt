package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    lateinit var allData: MutableLiveData<List<FavoriteData>>

    init {
        allData = MutableLiveData()
        loadRecords()
    }

    fun addProductsFavorite(favoriteData: FavoriteData) {
        repository.addProductFavorite(favoriteData)
        loadRecords()
    }

    fun deleteProductsFavorite(product_id :Int) {
        repository.deleteProductFavorite(product_id)
        loadRecords()
    }

    private fun loadRecords() {
        val list = repository.readAllData
        allData.postValue(list)
    }


}
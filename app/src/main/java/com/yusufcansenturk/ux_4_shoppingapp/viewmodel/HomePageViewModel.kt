package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: RetrofitRepository
) : ViewModel() {

    var allProductsList : MutableLiveData<ProductsItem>


    init {
        allProductsList = MutableLiveData()
    }

    fun getAllProductsObserverLiveData() : MutableLiveData<ProductsItem> {
        return allProductsList
    }

    fun loadData() {
        repository.getAllProducts(allProductsList)
    }


}
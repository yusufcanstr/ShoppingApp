package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.ux_4_shoppingapp.models.Products
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: RetrofitRepository
) : ViewModel() {

    val allProductsList : MutableLiveData<List<ProductsItem>>
    val menProductsList : MutableLiveData<List<ProductsItem>>
    val womanProductsList : MutableLiveData<List<ProductsItem>>


    init {
        allProductsList = MutableLiveData()
        menProductsList = MutableLiveData()
        womanProductsList = MutableLiveData()
    }

    fun getObserveLiveData(number:Int) : MutableLiveData<List<ProductsItem>> {
        if (number == 0) {
            return allProductsList
        }else if (number == 1) {
            return menProductsList
        }else if (number == 2) {
            return womanProductsList
        }else {
            return MutableLiveData(null)
        }
    }

    fun loadData() {
        repository.getAllProducts(allProductsList)
        repository.getMenProducts(menProductsList)
        repository.getWomanProducts(womanProductsList)
    }


}
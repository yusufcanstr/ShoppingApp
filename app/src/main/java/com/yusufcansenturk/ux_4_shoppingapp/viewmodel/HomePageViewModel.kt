package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: RetrofitRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val allProductsList : MutableLiveData<List<ProductsItem>> = MutableLiveData()
    private val menProductsList : MutableLiveData<List<ProductsItem>> = MutableLiveData()
    private val womanProductsList : MutableLiveData<List<ProductsItem>> = MutableLiveData()
    private val singleProduct: MutableLiveData<ProductsItem> = MutableLiveData()


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

    fun addFavoriteProduct(product: ProductsItem) {
        favoriteRepository.addProductFavorite(favoriteRepository.convertProductToFavorite(product))
    }

    fun loadData() {
        repository.getAllProducts(allProductsList)
        repository.getMenProducts(menProductsList)
        repository.getWomanProducts(womanProductsList)
    }

    fun loadSingleProduct(id:Int) {
        repository.getSingleProduct(id, singleProduct)
    }

    fun getSingleProductObserveLiveData() : MutableLiveData<ProductsItem> {
        return singleProduct
    }

}
package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import com.yusufcansenturk.ux_4_shoppingapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: RetrofitRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val productsList = MutableLiveData<List<ProductsItem>>()
    val productsLoading =MutableLiveData<Boolean>()
    private var _productsError = MutableLiveData<Boolean>()
    val productsError = _productsError

    fun loadProductsList(type:String) {
        when (type) {
            Constants.ALL_PRODUCTS -> {
                repository.getAllProducts(productsList)
            }
            Constants.WOMEN -> {
                repository.getWomanProducts(productsList)
            }
            Constants.MEN -> {
                repository.getMenProducts(productsList)
            }
            else -> {}
        }
    }

    fun getObserveLiveData(number:Int) : MutableLiveData<List<ProductsItem>> {
        return when (number) {
            0 -> {
                productsList
            }
            1 -> {
                MutableLiveData(null)
            }
            2 -> {
                MutableLiveData(null)
            }
            else -> {
                MutableLiveData(null)
            }
        }
    }

    fun addProductFavorites(productsItem: ProductsItem) {
        favoriteRepository.addProductFavorite(favoriteRepository.convertProductToFavorite(productsItem))
    }


}
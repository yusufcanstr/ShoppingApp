package com.yusufcansenturk.ux_4_shoppingapp.di.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import com.yusufcansenturk.ux_4_shoppingapp.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val retrofitServiceInstance: RetrofitServiceInstance
) {

    fun getAllProducts(liveData: MutableLiveData<List<ProductsItem>>) {
        retrofitServiceInstance.getAllProducts().enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(call: Call<List<ProductsItem>>, response: Response<List<ProductsItem>>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getMenProducts(liveData: MutableLiveData<List<ProductsItem>>) {
        retrofitServiceInstance.getAllProductsMen().enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>,
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getWomanProducts(liveData: MutableLiveData<List<ProductsItem>>) {
        retrofitServiceInstance.getAllProductsWoman().enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>,
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getSingleProduct(productId: Int, liveData: MutableLiveData<ProductsItem>){
        retrofitServiceInstance.getSingleProduct(productId).enqueue(object : Callback<ProductsItem> {
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}
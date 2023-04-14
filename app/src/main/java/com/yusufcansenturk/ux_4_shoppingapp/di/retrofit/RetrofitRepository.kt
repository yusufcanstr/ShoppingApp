package com.yusufcansenturk.ux_4_shoppingapp.di.retrofit

import androidx.lifecycle.MutableLiveData
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val retrofitServiceInstance: RetrofitServiceInstance
) {

    fun getAllProducts(liveData: MutableLiveData<ProductsItem>) {
        retrofitServiceInstance.getAllProducts().enqueue(object : Callback<ProductsItem>{
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

}
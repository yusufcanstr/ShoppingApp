package com.yusufcansenturk.ux_4_shoppingapp.di.retrofit

import com.yusufcansenturk.ux_4_shoppingapp.models.Categories
import com.yusufcansenturk.ux_4_shoppingapp.models.Products
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiceInstance {

    @GET("products")
    fun getAllProducts(): Call<List<ProductsItem>>

    @GET("products/{id}")
    fun getSingleProduct(@Path("id") id:Int) : Call<ProductsItem>

    @GET("products?limit={limit}")
    fun getLimitResults(@Path("limit") limit:Int) : Call<List<ProductsItem>>

    @GET("products/categories")
    fun getAllCategories(): Call<List<ProductsItem>>

    @GET("products/category/jewelery")
    fun getAllProductsJewelery() : Call<List<ProductsItem>>

    @GET("products/category/electronics")
    fun getAllProductsElectronics() : Call<List<ProductsItem>>

    @GET("products/category/men's clothing")
    fun getAllProductsMen() : Call<List<ProductsItem>>

    @GET("products/category/women's clothing")
    fun getAllProductsWoman() : Call<List<ProductsItem>>

}
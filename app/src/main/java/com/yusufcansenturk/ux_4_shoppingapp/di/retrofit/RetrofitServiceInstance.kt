package com.yusufcansenturk.ux_4_shoppingapp.di.retrofit

import com.yusufcansenturk.ux_4_shoppingapp.models.Categories
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiceInstance {

    @GET("products")
    fun getAllProducts(): Call<ProductsItem>

    @GET("products/{id}")
    fun getSingleProduct(@Path("id") id:String) : Call<ProductsItem>

    @GET("products?limit={limit}")
    fun getLimitResults(@Path("limit") limit:Int) : Call<ProductsItem>

    @GET("products/categories")
    fun getAllCategories(): Call<Categories>

    @GET("products/category/jewelery")
    fun getAllProductsJewelery() : Call<ProductsItem>

    @GET("products/category/electronics")
    fun getAllProductsElectronics() : Call<ProductsItem>

    @GET("products/category/men's clothing")
    fun getAllProductsMen() : Call<ProductsItem>

    @GET("products/category/women's clothing")
    fun getAllProductsWoman() : Call<ProductsItem>

}
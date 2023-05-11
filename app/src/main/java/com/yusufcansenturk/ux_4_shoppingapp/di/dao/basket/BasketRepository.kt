package com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket

import android.util.Log
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val dao: BasketDao,
) {

    var readAllData: List<BasketData> = dao.getReadAllData()

    fun addBasketProduct(basketData: BasketData) {
        dao.insertProduct(basketData)
    }

    fun deleteAllBasket() {
        try {
            dao.deleteAllBasket()
        }catch (e :Exception){
            Log.e("BasketLocalDatabase",e.message.toString())
        }
    }

    fun deleteProductBasket(basketData: BasketData) {
        dao.deleteProductBasket(basketData)
    }

    fun totalPriceProducts(basketList: List<BasketData>): Double {
        var totalPrice = 0.0
        for (i in basketList) {
            totalPrice += i.price * i.productQuantity
        }
        return totalPrice
    }


    fun increaseQuantity(basketData: BasketData) {
        dao.increaseQuantity(basketData.product_id)
    }

    fun decreaseQuantity(basketData: BasketData) {
        dao.decreaseQuantity(basketData.product_id)
    }



}
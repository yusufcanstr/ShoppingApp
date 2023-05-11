package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketData
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import com.yusufcansenturk.ux_4_shoppingapp.models.Rating
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    val readAllData: List<FavoriteData> = favoriteDao.readAllData()

    fun addProductFavorite(favoriteData: FavoriteData) {
        favoriteDao.addFavorite(favoriteData)
    }

    fun deleteProductFavorite(product_id :Int) {
        favoriteDao.removeProduct(product_id)
    }

    fun convertProductToFavorite(productsItem: ProductsItem) : FavoriteData {
        val favoriteData = FavoriteData(productsItem.id, productsItem.title,productsItem.price,productsItem.description,productsItem.category,productsItem.image,productsItem.rating.rate,productsItem.rating.count)
        return favoriteData
    }

    fun convertFavoriteToBasket(favoriteData: FavoriteData): BasketData {
        val basketData = BasketData( favoriteData.product_id, favoriteData.title,1.0,favoriteData.price,favoriteData.description,favoriteData.category,favoriteData.imageUrl,favoriteData.product_rate,favoriteData.product_count)
        return basketData
    }

}
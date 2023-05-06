package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

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

}
package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun addFavorite(favoriteData:FavoriteData)

    @Query("DELETE FROM favoriteTable WHERE product_id = :product_id")
    fun removeProduct(product_id:Int)

    @Query("SELECT * FROM favoriteTable")
    fun readAllData(): List<FavoriteData>

}
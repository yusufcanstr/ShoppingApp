package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favoriteData:FavoriteData)

    @Query("DELETE FROM favoriteTable WHERE product_id = :product_id")
    fun removeProduct(product_id:Int)

    @Query("SELECT * FROM favoriteTable")
    fun readAllData(): List<FavoriteData>

}
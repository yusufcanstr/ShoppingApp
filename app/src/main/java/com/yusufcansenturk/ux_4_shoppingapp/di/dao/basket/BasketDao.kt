package com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket

import androidx.room.*

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(basketData: BasketData)

    @Query("SELECT * FROM basketdatabase")
    fun getReadAllData():List<BasketData>

    @Delete
    fun deleteProductBasket(product: BasketData)

    @Query("Delete from basketdatabase")
    fun deleteAllBasket()

    @Update
    fun updateProductBasket(basketData: BasketData)

    @Query("SELECT SUM(price*productQuantity) FROM basketdatabase")
    fun getTotalPrice(): Double

    @Query("UPDATE basketdatabase SET productQuantity = productQuantity + 1.0 WHERE product_id = :product_id")
    fun increaseQuantity(product_id: Int)

    @Query("UPDATE basketdatabase SET productQuantity = productQuantity - 1.0 WHERE product_id = :product_id AND productQuantity > 0")
    fun decreaseQuantity(product_id: Int)

}
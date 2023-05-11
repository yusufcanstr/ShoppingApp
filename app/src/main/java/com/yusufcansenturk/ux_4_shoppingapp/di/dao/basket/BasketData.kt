package com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basketdatabase")
data class BasketData(
    @PrimaryKey(autoGenerate = false)
    val product_id:Int,
    val title:String,
    val productQuantity:Double,
    val price:Double,
    val description:String,
    val category:String,
    val imageUrl:String,
    val product_rate:Double,
    val product_count:Int
)

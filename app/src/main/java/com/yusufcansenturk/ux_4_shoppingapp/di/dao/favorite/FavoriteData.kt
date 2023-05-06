package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteTable")
data class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val product_id:Int,
    val title:String,
    val price:Double,
    val description:String,
    val category:String,
    val imageUrl:String,
    val product_rate:Double,
    val product_count:Int
)

package com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BasketData::class], version = 1, exportSchema = false)
abstract class BasketDatabase : RoomDatabase(){
    abstract fun getDAO() : BasketDao

    companion object {

        private var dbINSTANCE: BasketDatabase? = null

        fun getBasketDB(context: Context): BasketDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<BasketDatabase>(
                    context.applicationContext,
                    BasketDatabase::class.java,
                    "basketdatabase"
                ).allowMainThreadQueries().build()
            }
            return dbINSTANCE!!
        }

    }
}
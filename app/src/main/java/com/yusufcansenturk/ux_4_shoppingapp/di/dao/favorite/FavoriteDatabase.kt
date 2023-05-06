package com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteData::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getDAO() : FavoriteDao

    companion object {

        private var dbINSTANCE: FavoriteDatabase? = null

        fun getFavoriteDB(context: Context): FavoriteDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<FavoriteDatabase>(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favoriteTable"
                ).allowMainThreadQueries().build()
            }
            return dbINSTANCE!!
        }

    }

}
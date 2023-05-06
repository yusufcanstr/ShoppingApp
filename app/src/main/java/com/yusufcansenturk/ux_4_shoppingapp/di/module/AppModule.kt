package com.yusufcansenturk.ux_4_shoppingapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteDao
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteDatabase
import com.yusufcansenturk.ux_4_shoppingapp.di.retrofit.RetrofitServiceInstance
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import com.yusufcansenturk.ux_4_shoppingapp.utils.Constants
import com.yusufcansenturk.ux_4_shoppingapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSessionManager(preferences: SharedPreferences) = AppSessionManager(preferences)

    @Provides
    @Singleton
    fun getFavoriteDB(context: Application): FavoriteDatabase {
        return FavoriteDatabase.getFavoriteDB(context)
    }

    @Provides
    @Singleton
    fun getFavoriteDao(DB: FavoriteDatabase): FavoriteDao {
        return DB.getDAO()
    }


    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
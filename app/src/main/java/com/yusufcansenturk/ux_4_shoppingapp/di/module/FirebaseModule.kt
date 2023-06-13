package com.yusufcansenturk.ux_4_shoppingapp.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcansenturk.ux_4_shoppingapp.di.firebase.AuthRepository
import com.yusufcansenturk.ux_4_shoppingapp.di.firebase.AuthRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

}
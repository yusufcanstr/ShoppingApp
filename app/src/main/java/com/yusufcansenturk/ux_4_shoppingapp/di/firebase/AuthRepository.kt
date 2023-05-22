package com.yusufcansenturk.ux_4_shoppingapp.di.firebase

import com.google.firebase.auth.FirebaseUser
import com.yusufcansenturk.ux_4_shoppingapp.utils.AuthResource


interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email:String, password:String) : AuthResource<FirebaseUser>
    suspend fun signup(name:String, email:String, password: String): AuthResource<FirebaseUser>
    fun logout()
}
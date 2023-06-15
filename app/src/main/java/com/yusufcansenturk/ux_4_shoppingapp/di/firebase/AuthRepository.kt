package com.yusufcansenturk.ux_4_shoppingapp.di.firebase

import com.yusufcansenturk.ux_4_shoppingapp.models.User
import com.yusufcansenturk.ux_4_shoppingapp.utils.UiState


interface AuthRepository {
    fun registerUser(email: String, password: String, user: User, result: (UiState<String>) -> Unit)
    fun updateUserInfo(user: User, result: (UiState<String>) -> Unit)
    fun loginUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(user: User, result: (UiState<String>) -> Unit)
}
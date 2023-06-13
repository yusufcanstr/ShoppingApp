package com.yusufcansenturk.ux_4_shoppingapp.utils

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Failure(val error: String?): UiState<Nothing>()
}
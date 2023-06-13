package com.yusufcansenturk.ux_4_shoppingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.ux_4_shoppingapp.di.firebase.AuthRepository
import com.yusufcansenturk.ux_4_shoppingapp.models.User
import com.yusufcansenturk.ux_4_shoppingapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){

    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>>
        get() = _register

    fun register(
        email: String,
        password: String,
        user: User
    ) {
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = user
        ) { _register.value = it }
    }


}
package com.yusufcansenturk.ux_4_shoppingapp.prefs

import android.content.SharedPreferences
import com.yusufcansenturk.ux_4_shoppingapp.utils.Constants
import javax.inject.Inject

class AppSessionManager @Inject constructor(
    private val preferences: SharedPreferences
){

    fun getTheme() = preferences.getBoolean(Constants.THEME_KEY, false)

    fun setTheme(value:Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(Constants.THEME_KEY, value)
        editor.apply()
    }

    fun getIsFirstRun() = preferences.getBoolean(Constants.FIRST_RUN_KEY, true)

    fun setIsFirstRun(value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(Constants.FIRST_RUN_KEY, value)
        editor.apply()
    }

}
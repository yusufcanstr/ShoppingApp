package com.yusufcansenturk.ux_4_shoppingapp.utils

import android.view.View

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.disable(){
    isEnabled = false
}

fun View.enabled(){
    isEnabled = true
}
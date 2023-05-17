package com.yusufcansenturk.ux_4_shoppingapp.utils

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


fun SuccessToast(activity: Activity, message:String) {
    MotionToast.createToast(
        activity,
        "Başarılı",
        message,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

fun ErrorToast(activity: Activity, message:String) {
    MotionToast.createToast(
        activity,
        "Hata",
        message,
        MotionToastStyle.ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

fun WarningToast(activity: Activity, message:String) {
    MotionToast.createToast(
        activity,
        "Uyarı",
        message,
        MotionToastStyle.WARNING,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

fun InfoToast(activity: Activity, message:String) {
    MotionToast.createToast(
        activity,
        "Bilgi",
        message,
        MotionToastStyle.INFO,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

fun DeleteToast(activity: Activity, message:String) {
    MotionToast.createToast(
        activity,
        "Bilgi",
        message,
        MotionToastStyle.DELETE,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}
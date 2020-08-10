package com.gan.breakingbad.utils

import android.content.Context

object ViewUtils {

    fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.getResources().getDisplayMetrics()
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 200).toInt()
    }
}
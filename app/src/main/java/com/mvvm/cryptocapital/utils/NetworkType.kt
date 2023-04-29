package com.mvvm.cryptocapital.utils

import androidx.annotation.IntDef

object NetworkType {
    const val DEFAULT = 11000
    const val SCANNED_WIFI = 12000
    const val UNKNOWN = -1

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(DEFAULT, SCANNED_WIFI, UNKNOWN)
    annotation class Value
}

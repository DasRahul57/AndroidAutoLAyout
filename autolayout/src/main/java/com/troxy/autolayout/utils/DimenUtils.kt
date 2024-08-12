package com.troxy.autolayout.utils

import android.util.TypedValue

object DimenUtils {
    private fun getComplexUnit(data: Int): Int {
        return TypedValue.COMPLEX_UNIT_MASK and (data shr TypedValue.COMPLEX_UNIT_SHIFT)
    }

    fun isPxVal(`val`: TypedValue?): Boolean {
        if (`val` != null && `val`.type == TypedValue.TYPE_DIMENSION && getComplexUnit(
                `val`.data
            ) == TypedValue.COMPLEX_UNIT_PX
        ) {
            return true
        }
        return false
    }
}

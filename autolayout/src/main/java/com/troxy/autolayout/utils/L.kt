package com.troxy.autolayout.utils

import android.util.Log

object L {
    var debug: Boolean = false
    private const val TAG = "AUTO_LAYOUT"

    fun e(msg: String?) {
        if (debug) {
            Log.e(TAG, msg!!)
        }
    }
}

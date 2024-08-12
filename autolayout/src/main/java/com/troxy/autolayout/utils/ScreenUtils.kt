package com.troxy.autolayout.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

object ScreenUtils {
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        try {
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
        return result
    }


    fun getScreenSize(context: Context, useDeviceSize: Boolean): IntArray {
        val size = IntArray(2)

        val w = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = w.defaultDisplay
        val metrics = DisplayMetrics()
        d.getMetrics(metrics)
        // since SDK_INT = 1;
        var widthPixels = metrics.widthPixels
        var heightPixels = metrics.heightPixels

        if (!useDeviceSize) {
            size[0] = widthPixels
            size[1] = heightPixels - getStatusBarHeight(context)

            return size
        }

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) try {
            widthPixels = Display::class.java.getMethod("getRawWidth").invoke(d) as Int
            heightPixels = Display::class.java.getMethod("getRawHeight").invoke(d) as Int
        } catch (ignored: Exception) {
        }
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17) try {
            val realSize = Point()
            Display::class.java.getMethod("getRealSize", Point::class.java).invoke(d, realSize)
            widthPixels = realSize.x
            heightPixels = realSize.y
        } catch (ignored: Exception) {
        }
        size[0] = widthPixels
        size[1] = heightPixels
        return size
    }
}

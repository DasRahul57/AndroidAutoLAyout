package com.troxy.autolayout.config

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.troxy.autolayout.utils.L
import com.troxy.autolayout.utils.ScreenUtils

class AutoLayoutConifg
private constructor() {
    var screenWidth: Int = 0
        private set
    var screenHeight: Int = 0
        private set

    var designWidth: Int = 0
        private set
    var designHeight: Int = 0
        private set

    private var useDeviceSize = false


    fun checkParams() {
        if (designHeight <= 0 || designWidth <= 0) {
            throw RuntimeException(
                "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file."
            )
        }
    }

    fun useDeviceSize(): AutoLayoutConifg {
        useDeviceSize = true
        return this
    }


    fun init(context: Context) {
        getMetaData(context)

        val screenSize = ScreenUtils.getScreenSize(context, useDeviceSize)
        screenWidth = screenSize[0]
        screenHeight = screenSize[1]
        L.e(" screenWidth =" + screenWidth + " ,screenHeight = " + screenHeight)
    }

    private fun getMetaData(context: Context) {
        val packageManager = context.packageManager
        val applicationInfo: ApplicationInfo
        try {
            applicationInfo = packageManager.getApplicationInfo(
                context
                    .packageName, PackageManager.GET_META_DATA
            )
            if (applicationInfo?.metaData != null) {
                designWidth = applicationInfo.metaData[KEY_DESIGN_WIDTH] as Int
                designHeight = applicationInfo.metaData[KEY_DESIGN_HEIGHT] as Int
            }
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException(
                "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.",
                e
            )
        }

        L.e(" designWidth =" + designWidth + " , designHeight = " + designHeight)
    }


    companion object {
        val instance: AutoLayoutConifg = AutoLayoutConifg()


        private const val KEY_DESIGN_WIDTH = "design_width"
        private const val KEY_DESIGN_HEIGHT = "design_height"
    }
}

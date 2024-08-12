package com.troxy.autolayout.attr

import android.view.View

class MaxWidthAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MAX_WIDTH
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        try {
            val setMaxWidthMethod =
                view!!.javaClass.getMethod("setMaxWidth", Int::class.javaPrimitiveType)
            setMaxWidthMethod.invoke(view, `val`)
        } catch (ignore: Exception) {
        }
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MaxWidthAttr? {
            var attr: MaxWidthAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MaxWidthAttr(`val`, Attrs.MAX_WIDTH, 0)
                BASE_HEIGHT -> attr = MaxWidthAttr(`val`, 0, Attrs.MAX_WIDTH)
                BASE_DEFAULT -> attr = MaxWidthAttr(`val`, 0, 0)
            }
            return attr
        }

        @JvmStatic
        fun getMaxWidth(view: View): Int {
            try {
                val setMaxWidthMethod = view.javaClass.getMethod("getMaxWidth")
                return setMaxWidthMethod.invoke(view) as Int
            } catch (ignore: Exception) {
            }
            return 0
        }
    }
}

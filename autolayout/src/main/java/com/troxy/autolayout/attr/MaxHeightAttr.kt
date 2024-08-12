package com.troxy.autolayout.attr

import android.view.View

class MaxHeightAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MAX_HEIGHT
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        try {
            val setMaxWidthMethod =
                view!!.javaClass.getMethod("setMaxHeight", Int::class.javaPrimitiveType)
            setMaxWidthMethod.invoke(view, `val`)
        } catch (ignore: Exception) {
        }
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MaxHeightAttr? {
            var attr: MaxHeightAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MaxHeightAttr(`val`, Attrs.MAX_HEIGHT, 0)
                BASE_HEIGHT -> attr = MaxHeightAttr(`val`, 0, Attrs.MAX_HEIGHT)
                BASE_DEFAULT -> attr = MaxHeightAttr(`val`, 0, 0)
            }
            return attr
        }

        @JvmStatic
        fun getMaxHeight(view: View): Int {
            try {
                val setMaxWidthMethod = view.javaClass.getMethod("getMaxHeight")
                return setMaxWidthMethod.invoke(view) as Int
            } catch (ignore: Exception) {
            }
            return 0
        }
    }
}

package com.troxy.autolayout.attr

import android.os.Build
import android.view.View

class MinHeightAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MIN_HEIGHT
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        try {
            view!!.minimumHeight = `val`
            //            Method setMaxWidthMethod = view.getClass().getMethod("setMinHeight", int.class);
//            setMaxWidthMethod.invoke(view, val);
        } catch (ignore: Exception) {
        }
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MinHeightAttr? {
            var attr: MinHeightAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MinHeightAttr(`val`, Attrs.MIN_HEIGHT, 0)
                BASE_HEIGHT -> attr = MinHeightAttr(`val`, 0, Attrs.MIN_HEIGHT)
                BASE_DEFAULT -> attr = MinHeightAttr(`val`, 0, 0)
            }
            return attr
        }

        @JvmStatic
        fun getMinHeight(view: View): Int {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return view.minimumHeight
            } else {
                try {
                    val minHeight = view.javaClass.getField("mMinHeight")
                    minHeight.isAccessible = true
                    return minHeight[view] as Int
                } catch (e: Exception) {
                }
            }

            return 0
        }
    }
}

package com.troxy.autolayout.attr

import android.view.View

class WidthAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.WIDTH
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        val lp = view!!.layoutParams
        lp.width = `val`
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): WidthAttr? {
            var widthAttr: WidthAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> widthAttr = WidthAttr(`val`, Attrs.WIDTH, 0)
                BASE_HEIGHT -> widthAttr = WidthAttr(`val`, 0, Attrs.WIDTH)
                BASE_DEFAULT -> widthAttr = WidthAttr(`val`, 0, 0)
            }
            return widthAttr
        }
    }
}

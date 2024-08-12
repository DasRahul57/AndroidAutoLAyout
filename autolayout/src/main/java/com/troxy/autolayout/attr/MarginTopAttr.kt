package com.troxy.autolayout.attr

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class MarginTopAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MARGIN_TOP
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        if (view!!.layoutParams !is MarginLayoutParams) {
            return
        }
        val lp = view.layoutParams as MarginLayoutParams
        lp.topMargin = `val`
    }


    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MarginTopAttr? {
            var attr: MarginTopAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MarginTopAttr(`val`, Attrs.MARGIN_TOP, 0)
                BASE_HEIGHT -> attr = MarginTopAttr(`val`, 0, Attrs.MARGIN_TOP)
                BASE_DEFAULT -> attr = MarginTopAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

package com.troxy.autolayout.attr

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class MarginLeftAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MARGIN_LEFT
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        if (view!!.layoutParams !is MarginLayoutParams) {
            return
        }
        val lp = view.layoutParams as MarginLayoutParams
        lp.leftMargin = `val`
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MarginLeftAttr? {
            var attr: MarginLeftAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MarginLeftAttr(`val`, Attrs.MARGIN_LEFT, 0)
                BASE_HEIGHT -> attr = MarginLeftAttr(`val`, 0, Attrs.MARGIN_LEFT)
                BASE_DEFAULT -> attr = MarginLeftAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

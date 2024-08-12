package com.troxy.autolayout.attr

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class MarginBottomAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MARGIN_BOTTOM
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        if (view!!.layoutParams !is MarginLayoutParams) {
            return
        }
        val lp = view.layoutParams as MarginLayoutParams
        lp.bottomMargin = `val`
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MarginBottomAttr? {
            var attr: MarginBottomAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MarginBottomAttr(`val`, Attrs.MARGIN_BOTTOM, 0)
                BASE_HEIGHT -> attr = MarginBottomAttr(`val`, 0, Attrs.MARGIN_BOTTOM)
                BASE_DEFAULT -> attr = MarginBottomAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

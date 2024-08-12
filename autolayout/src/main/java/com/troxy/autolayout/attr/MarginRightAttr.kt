package com.troxy.autolayout.attr

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class MarginRightAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MARGIN_RIGHT
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        if (view!!.layoutParams !is MarginLayoutParams) {
            return
        }
        val lp = view.layoutParams as MarginLayoutParams
        lp.rightMargin = `val`
    }


    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): MarginRightAttr? {
            var attr: MarginRightAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = MarginRightAttr(`val`, Attrs.MARGIN_RIGHT, 0)
                BASE_HEIGHT -> attr = MarginRightAttr(`val`, 0, Attrs.MARGIN_RIGHT)
                BASE_DEFAULT -> attr = MarginRightAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

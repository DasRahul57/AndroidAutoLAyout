package com.troxy.autolayout.attr

import android.view.View

class PaddingBottomAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.PADDING_BOTTOM
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        val l = view!!.paddingLeft
        val t = view.paddingTop
        val r = view.paddingRight
        val b = `val`
        view.setPadding(l, t, r, b)
    }


    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): PaddingBottomAttr? {
            var attr: PaddingBottomAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = PaddingBottomAttr(`val`, Attrs.PADDING_BOTTOM, 0)
                BASE_HEIGHT -> attr = PaddingBottomAttr(`val`, 0, Attrs.PADDING_BOTTOM)
                BASE_DEFAULT -> attr = PaddingBottomAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

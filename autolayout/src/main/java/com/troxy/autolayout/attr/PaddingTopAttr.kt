package com.troxy.autolayout.attr

import android.view.View

class PaddingTopAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.PADDING_TOP
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        val l = view!!.paddingLeft
        val t = `val`
        val r = view.paddingRight
        val b = view.paddingBottom
        view.setPadding(l, t, r, b)
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): PaddingTopAttr? {
            var attr: PaddingTopAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = PaddingTopAttr(`val`, Attrs.PADDING_TOP, 0)
                BASE_HEIGHT -> attr = PaddingTopAttr(`val`, 0, Attrs.PADDING_TOP)
                BASE_DEFAULT -> attr = PaddingTopAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

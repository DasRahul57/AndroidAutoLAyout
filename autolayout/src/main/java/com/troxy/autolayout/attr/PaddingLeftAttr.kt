package com.troxy.autolayout.attr

import android.view.View

class PaddingLeftAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.PADDING_LEFT
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        val l = `val`
        val t = view!!.paddingTop
        val r = view.paddingRight
        val b = view.paddingBottom
        view.setPadding(l, t, r, b)
    }


    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): PaddingLeftAttr? {
            var attr: PaddingLeftAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = PaddingLeftAttr(`val`, Attrs.PADDING_LEFT, 0)
                BASE_HEIGHT -> attr = PaddingLeftAttr(`val`, 0, Attrs.PADDING_LEFT)
                BASE_DEFAULT -> attr = PaddingLeftAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

package com.troxy.autolayout.attr

import android.view.View

class PaddingRightAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.PADDING_RIGHT
    }

    override fun defaultBaseWidth(): Boolean {
        return true
    }

    override fun execute(view: View?, `val`: Int) {
        val l = view!!.paddingLeft
        val t = view.paddingTop
        val r = `val`
        val b = view.paddingBottom
        view.setPadding(l, t, r, b)
    }


    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): PaddingRightAttr? {
            var attr: PaddingRightAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = PaddingRightAttr(`val`, Attrs.PADDING_RIGHT, 0)
                BASE_HEIGHT -> attr = PaddingRightAttr(`val`, 0, Attrs.PADDING_RIGHT)
                BASE_DEFAULT -> attr = PaddingRightAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

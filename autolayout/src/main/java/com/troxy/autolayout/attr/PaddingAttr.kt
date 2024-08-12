package com.troxy.autolayout.attr

import android.view.View

class PaddingAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.PADDING
    }

    override fun apply(view: View) {
        val l: Int
        val t: Int
        val r: Int
        val b: Int
        if (useDefault()) {
            r = percentWidthSize
            l = r
            b = percentHeightSize
            t = b
            view.setPadding(l, t, r, b)
            return
        }
        super.apply(view)
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        view!!.setPadding(`val`, `val`, `val`, `val`)
    }
}

package com.troxy.autolayout.attr

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

class MarginAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.MARGIN
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun apply(view: View) {
        if (view.layoutParams !is MarginLayoutParams) {
            return
        }
        if (useDefault()) {
            val lp = view.layoutParams as MarginLayoutParams
            lp.rightMargin = percentWidthSize
            lp.leftMargin = lp.rightMargin
            lp.bottomMargin = percentHeightSize
            lp.topMargin = lp.bottomMargin
            return
        }
        super.apply(view)
    }

    override fun execute(view: View?, `val`: Int) {
        val lp = view!!.layoutParams as MarginLayoutParams
        lp.bottomMargin = `val`
        lp.topMargin = lp.bottomMargin
        lp.rightMargin = lp.topMargin
        lp.leftMargin = lp.rightMargin
    }
}

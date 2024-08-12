package com.troxy.autolayout.attr

import android.util.TypedValue
import android.view.View
import android.widget.TextView

class TextSizeAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.TEXTSIZE
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        if (view !is TextView) return
        view.includeFontPadding = false
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, `val`.toFloat())
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): TextSizeAttr? {
            var attr: TextSizeAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> attr = TextSizeAttr(`val`, Attrs.TEXTSIZE, 0)
                BASE_HEIGHT -> attr = TextSizeAttr(`val`, 0, Attrs.TEXTSIZE)
                BASE_DEFAULT -> attr = TextSizeAttr(`val`, 0, 0)
            }
            return attr
        }
    }
}

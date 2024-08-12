package com.troxy.autolayout.attr

import android.view.View

class HeightAttr(pxVal: Int, baseWidth: Int, baseHeight: Int) :
    AutoAttr(pxVal, baseWidth, baseHeight) {
    override fun attrVal(): Int {
        return Attrs.HEIGHT
    }

    override fun defaultBaseWidth(): Boolean {
        return false
    }

    override fun execute(view: View?, `val`: Int) {
        val lp = view!!.layoutParams
        lp.height = `val`
    }

    companion object {
        @JvmStatic
        fun generate(`val`: Int, baseFlag: Int): HeightAttr? {
            var heightAttr: HeightAttr? = null
            when (baseFlag) {
                BASE_WIDTH -> heightAttr = HeightAttr(`val`, Attrs.HEIGHT, 0)
                BASE_HEIGHT -> heightAttr = HeightAttr(`val`, 0, Attrs.HEIGHT)
                BASE_DEFAULT -> heightAttr = HeightAttr(`val`, 0, 0)
            }
            return heightAttr
        }
    }
}
